package rest

import model.Result
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface RestService {

    @GET("subscriptions")
    fun getSubscriptions(@Header("Authorization") credentials:String):Call<Result>

    @POST("destination")
    fun sendMessage(@Header("Authorization") credentials:String, @Header("exchange") exchange:String, @Header("routingKey") routingKey:String,  @Body body: RequestBody):Call<Result>

}