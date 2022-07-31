package id.devdkz.moviestv.backend.repos.cloud.api

import id.devdkz.moviestv.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIConf
{
    fun getAPI() : GetAPI
    {
        val client = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
            })
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
        val connect = Retrofit.Builder()
            .baseUrl(BuildConfig.URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return connect.create(GetAPI::class.java)
    }
}