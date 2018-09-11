package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Subscription {

    @SerializedName("exchange")
    @Expose
    private var exchange: String? = null
    @SerializedName("routing_key")
    @Expose
    private var routingKey: String? = null

}