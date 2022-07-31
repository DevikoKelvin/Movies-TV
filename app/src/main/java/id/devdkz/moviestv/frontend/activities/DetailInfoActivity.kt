package id.devdkz.moviestv.frontend.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.devdkz.moviestv.BuildConfig
import id.devdkz.moviestv.R
import id.devdkz.moviestv.backend.data.MainData
import id.devdkz.moviestv.backend.dataconvert.Converter.mainDataToBookmarkEntity
import id.devdkz.moviestv.backend.viewmodel.FactoryViewModel
import id.devdkz.moviestv.backend.viewmodel.MainViewModel
import id.devdkz.moviestv.databinding.ActivityDetailInfoBinding
import kotlinx.coroutines.*

class DetailInfoActivity : AppCompatActivity()
{
    private lateinit var layoutBinds : ActivityDetailInfoBinding
    private lateinit var vm : MainViewModel
    private var togChecker : Boolean = false

    companion object
    {
        const val EXTRA_ID = "EXTRA_ID"
        const val EXTRA_TYPE = "EXTRA_TYPE"
    }

    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        layoutBinds = ActivityDetailInfoBinding.inflate(layoutInflater)
        setContentView(layoutBinds.root)

        vm = ViewModelProvider(
            this,
            FactoryViewModel.getInstance(this)
        )[MainViewModel::class.java]

        val id = intent.getIntExtra(EXTRA_ID, 0)
        Log.d("Detailed Data ID", id.toString())
        val cat = intent.getStringExtra(EXTRA_TYPE)
        Log.d("Detailed Data Category", cat.toString())

        showLoading(true)

        id.let {
            if (cat != null)
            {
                vm.getDetailData(cat, id).observe(this, { data ->
                    showLoading(false)
                    Log.d("Detailed data", data.toString())
                    cast(data)
                    layoutBinds.apply {
                        bookmarkBtn.setOnClickListener {
                            if (!togChecker)
                            {
                                vm.insertToBookmark(data.mainDataToBookmarkEntity(cat))
                                Toast.makeText(
                                    this@DetailInfoActivity,
                                    getString(R.string.bookmarked),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else
                            {
                                vm.deleteFromBookmark(data.mainDataToBookmarkEntity(cat))
                                Toast.makeText(
                                    this@DetailInfoActivity,
                                    getString(R.string.un_bookmarked),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            bookmarkBtn.isChecked = !togChecker
                        }

                        CoroutineScope(Dispatchers.IO).launch {
                            val i = vm.checkBookmark(id)
                            withContext(Dispatchers.Main) {
                                if (i > 0)
                                {
                                    bookmarkBtn.isChecked = true
                                    togChecker = true
                                }
                                else
                                {
                                    bookmarkBtn.isChecked = false
                                    togChecker = false
                                }
                            }
                        }

                        vm.getBookmarkDetail(data.id, cat)
                            .observe(this@DetailInfoActivity, { bookmark ->
                            bookmark?.let {
                                togChecker = true
                                bookmarkBtn.isChecked = true
                            }
                        })
                    }
                })
            }
        }
    }

    private fun cast(data : MainData) =
        layoutBinds.apply {
            Glide
                .with(applicationContext)
                .load(BuildConfig.IMG_URL + data.img)
                .into(imgView)
            titleTxt.text = data.title
            ratingTxt.text = data.rating.toString()
            reldateTxt.text = data.release
            synTxt.text = data.syn
        }


    private fun showLoading(cond : Boolean) =
        layoutBinds.apply {
            if (cond)
                loadingBar.visibility = View.VISIBLE
            else
                loadingBar.visibility = View.INVISIBLE
        }
}