package id.devdkz.moviestv.frontend.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

class TVFrags : Fragment(R.layout.fragment_main)
{
    private lateinit var layoutBinds : FragmentMainBinding

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

        val viewModel = ViewModelProvider(
            this,
            FactoryViewModel.getInstance(this.requireContext())
        )[MainViewModel::class.java]

        viewModel.getList("tv").observe(viewLifecycleOwner, {
            showLoading(false)
            Log.d("TV list data", it.toString())

            initRV(it)
        })
    }

    private fun initRV(data : PagedList<MainData>)
    {
        layoutBinds.rvView.apply {
            layoutManager = LinearLayoutManager(activity)
            val dataAdapter = MainAdapter()
            adapter = dataAdapter

            dataAdapter.submitList(data)
            dataAdapter.notifyDataSetChanged()
            dataAdapter.setOnItemClickCallback(object : MainAdapter.OnItemClickCallback
            {
                override fun onItemClicked(data : MainData)
                {
                    startActivity(
                        Intent(activity, DetailInfoActivity::class.java)
                            .putExtra(EXTRA_TYPE, "tv")
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