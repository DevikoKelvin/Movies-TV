package id.devdkz.moviestv.backend.misc

import androidx.test.espresso.idling.CountingIdlingResource

object IdlingRes
{
	private const val res = "GLOBAL"
	val idling = CountingIdlingResource(res)

	fun inc() = idling.increment()

	fun dec() = idling.decrement()
}