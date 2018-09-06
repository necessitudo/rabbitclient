package rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client() {

    companion object {

        fun create(baseUrl:String):SubscriptionsService {
            val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(SubscriptionsService::class.java)
        }
    }



}