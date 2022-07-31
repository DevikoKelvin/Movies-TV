package id.devdkz.moviestv.backend.repos.cloud.api

import id.devdkz.moviestv.BuildConfig
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetAPI
{
	@GET("{type}/popular?api_key=${BuildConfig.API_KEY}")
	fun getList(
		@Path("type")
		type : String,
	): Call<ResponseBody>
}