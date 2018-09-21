package rest

import com.google.gson.GsonBuilder

class NetworkHelper(val client:RestService, val authToken:String) {
    val gson    = GsonBuilder().create()
    val autoAck = false
}