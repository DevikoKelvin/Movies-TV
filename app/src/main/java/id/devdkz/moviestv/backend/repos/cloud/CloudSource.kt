package id.devdkz.moviestv.backend.repos.cloud

import id.devdkz.moviestv.backend.repos.cloud.api.APIConf
import id.devdkz.moviestv.backend.data.MainData
import id.devdkz.moviestv.backend.misc.IdlingRes
import org.json.JSONObject
import retrofit2.await

class CloudSource
{
    companion object
    {
        @Volatile
        private var instance : CloudSource? = null

        fun getInstance() : CloudSource =
            instance ?: synchronized(this) {
                instance ?: CloudSource()
            }
    }

    suspend fun getList(cat : String, callback : LoadPopularCallback)
    {
        val listData = ArrayList<MainData>()
        lateinit var title : String
        lateinit var date : String
        IdlingRes.inc()
        APIConf.getAPI().getList(cat).await().let { response ->
            val responseObject = JSONObject(response.string())
            val items = responseObject.getJSONArray("results")

            if (cat == "movie")
            {
                title = "title"
                date = "release_date"
            }
            else
            {
                title = "name"
                date = "first_air_date"
            }

            for (i in 0 until items.length())
            {
                val jsonObject = items.getJSONObject(i)
                val myData = MainData(
                    jsonObject.getInt("id"),
                    jsonObject.getString("poster_path"),
                    jsonObject.getString(title),
                    jsonObject.getDouble("vote_average"),
                    jsonObject.getString(date),
                    jsonObject.getString("overview")
                )
                listData.add(myData)
            }
            callback.onPopularReceived(listData)
            IdlingRes.dec()
        }
    }

    interface LoadPopularCallback
    {
        fun onPopularReceived(response : ArrayList<MainData>)
    }
}