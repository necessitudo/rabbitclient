package rest

import data.Result
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RestService {

    @GET("subscriptions")
    fun getSubscriptions(@Header("Authorization") credentials:String):Call<Result>

    @POST("destination")
    fun sendMessage(@Header("Authorization") credentials:String, @Body body: String):Call<Result>

}