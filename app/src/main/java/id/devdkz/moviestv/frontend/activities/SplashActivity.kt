package id.devdkz.moviestv.frontend.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import id.devdkz.moviestv.R

class SplashActivity : AppCompatActivity()
{
	companion object
	{
		const val timeDel: Long = 1500
	}

	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)

		Handler(Looper.getMainLooper())
			.postDelayed(
				{
					startActivity(
						Intent(
							this, HomeActivity::class.java
						)
					)
					finish()
				},
				timeDel
			)
	}
}