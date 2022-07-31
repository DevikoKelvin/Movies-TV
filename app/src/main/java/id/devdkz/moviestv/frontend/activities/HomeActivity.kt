package id.devdkz.moviestv.frontend.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import id.devdkz.moviestv.R
import id.devdkz.moviestv.backend.adapters.MainTabAdapter
import id.devdkz.moviestv.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity()
{
	private lateinit var layoutBinds : ActivityHomeBinding

	companion object
	{
		private val TABS = intArrayOf(
			R.string.movies, R.string.tv
		)
	}

	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		layoutBinds = ActivityHomeBinding.inflate(layoutInflater)
		setContentView(layoutBinds.root)

		supportActionBar?.title = getString(R.string.app_name)
		supportActionBar?.subtitle = getString(R.string.version)
		supportActionBar?.elevation = 0f

		layoutBinds.apply {
			pagerV.adapter = MainTabAdapter(this@HomeActivity)

			TabLayoutMediator(mainTab, pagerV) {tab, pos ->
				tab.text = resources.getString(TABS[pos])
			}.attach()
		}
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean
	{
		menuInflater.inflate(R.menu.menu_layout, menu)

		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean
	{
		when (item.itemId)
		{
			R.id.bookmark_menu ->
				startActivity(
					Intent(
						this, BookmarkActivity::class.java
					)
				)
		}

		return super.onOptionsItemSelected(item)
	}
}