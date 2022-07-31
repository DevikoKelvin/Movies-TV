package id.devdkz.moviestv.backend.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.devdkz.moviestv.frontend.fragments.MoviesFrags
import id.devdkz.moviestv.frontend.fragments.TVFrags

class MainTabAdapter(act : AppCompatActivity) : FragmentStateAdapter(act)
{
    override fun getItemCount() : Int = 2

    override fun createFragment(x : Int) : Fragment = when (x)
    {
        0 -> MoviesFrags()
        1 -> TVFrags()
        else -> Fragment()
    }
}