package id.devdkz.moviestv.frontend.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import id.devdkz.moviestv.R
import id.devdkz.moviestv.backend.adapters.MainAdapter
import id.devdkz.moviestv.backend.data.MainData
import id.devdkz.moviestv.backend.viewmodel.FactoryViewModel
import id.devdkz.moviestv.backend.viewmodel.MainViewModel
import id.devdkz.moviestv.databinding.FragmentMainBinding
import id.devdkz.moviestv.frontend.activities.DetailInfoActivity
import id.devdkz.moviestv.frontend.activities.DetailInfoActivity.Companion.EXTRA_ID
import id.devdkz.moviestv.frontend.activities.DetailInfoActivity.Companion.EXTRA_TYPE

class BookmarkMovFrags : Fragment(R.layout.fragment_main)
{
    private lateinit var layoutBinds : FragmentMainBinding
    private lateinit var vm : MainViewModel

    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?
    ) : View
    {
        layoutBinds = FragmentMainBinding.inflate(
            layoutInflater, container, false
        )

        return layoutBinds.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        showLoading(true)

        vm = ViewModelProvider(
            this,
            FactoryViewModel.getInstance(this.requireContext())
        )[MainViewModel::class.java]

        observeData()
    }

    override fun onResume()
    {
        super.onResume()
        showLoading(true)
        observeData()
    }

    private fun observeData() =
    vm.getListBookmark("movie").observe(viewLifecycleOwner, {
        showLoading(false)
        initRV(it)
    })

    private fun initRV(movieList : PagedList<MainData>)
    {
        layoutBinds.rvView.apply {
            layoutManager = LinearLayoutManager(activity)
            val dataAdapter = MainAdapter()
            adapter = dataAdapter

            dataAdapter.submitList(movieList)
            dataAdapter.notifyDataSetChanged()
            dataAdapter.setOnItemClickCallback(object : MainAdapter.OnItemClickCallback
            {
                override fun onItemClicked(data : MainData)
                {
                    startActivity(
                        Intent(activity, DetailInfoActivity::class.java)
                            .putExtra(EXTRA_TYPE, "movie")
                            .putExtra(EXTRA_ID, data.id)
                    )
                }
            })
        }
    }

    private fun showLoading(cond : Boolean) =
        layoutBinds.apply {
            if (cond)
                loadingBar.visibility = View.VISIBLE
            else
                loadingBar.visibility = View.INVISIBLE
        }
}