package rest

import model.Result
import retrofit2.Call
import retrofit2.http.*

interface RestService {

    @GET("subscriptions")
    fun getSubscriptions(@Header("Authorization") credentials:String):Call<Result>

    @POST("destination")
    fun sendMessage(@Header("Authorization") credentials:String,  @Body body: String):Call<Result>

}