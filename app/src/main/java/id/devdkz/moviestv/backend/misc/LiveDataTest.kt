package id.devdkz.moviestv.backend.misc

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@Suppress("UNCHECKED_CAST")
object LiveDataTest
{
    fun <T> getVal(data : LiveData<T>) : T
    {
        val arr = arrayOfNulls<Any>(1)
        val count = CountDownLatch(1)

        val obs = object : Observer<T>
        {
            override fun onChanged(t : T)
            {
                arr[0] = t
                count.countDown()
                data.removeObserver(this)
            }
        }

        data.observeForever(obs)

        try
        {
            count.await(2, TimeUnit.SECONDS)
        }
        catch (e: InterruptedException)
        {
            e.printStackTrace()
        }

        return arr[0] as T
    }
}