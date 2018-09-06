package rest

import data.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface SubscriptionsService {

    @GET("subscriptions")
    fun getSubscriptions(@Header("Authorization") credentials:String):Call<Result>

}