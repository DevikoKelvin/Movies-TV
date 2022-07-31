package id.devdkz.moviestv.backend.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.devdkz.moviestv.frontend.fragments.BookmarkMovFrags
import id.devdkz.moviestv.frontend.fragments.BookmarkTVFrags

class BookmarkTabAdapter(act : AppCompatActivity) : FragmentStateAdapter(act)
{
    override fun getItemCount() : Int = 2

    override fun createFragment(x : Int) : Fragment = when (x)
    {
        0 -> BookmarkMovFrags()
        1 -> BookmarkTVFrags()
        else -> Fragment()
    }
}