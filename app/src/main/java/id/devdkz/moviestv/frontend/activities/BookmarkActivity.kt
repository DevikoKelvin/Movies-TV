package id.devdkz.moviestv.frontend.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import id.devdkz.moviestv.R
import id.devdkz.moviestv.backend.adapters.BookmarkTabAdapter
import id.devdkz.moviestv.databinding.ActivityBookmarkBinding

class BookmarkActivity : AppCompatActivity()
{
    private lateinit var layoutBinds : ActivityBookmarkBinding

    companion object
    {
        private val TABS = intArrayOf(
            R.string.movies, R.string.tv
        )
    }

    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        layoutBinds = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(layoutBinds.root)

        supportActionBar?.title = getString(R.string.bookmark)

        layoutBinds.apply {
            pagerV.adapter = BookmarkTabAdapter(this@BookmarkActivity)

            TabLayoutMediator(mainTab, pagerV) {tab, pos ->
                tab.text = resources.getString(TABS[pos])
            }.attach()
        }
    }
}