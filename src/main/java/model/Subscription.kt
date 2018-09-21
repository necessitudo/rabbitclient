package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Subscription {

    @SerializedName("queue")
    @Expose
    val queue: String? = null

    @SerializedName("routing_key")
    @Expose
    val routingKey: String? = null

    override fun toString(): String {
        return "queue:$queue routing_key:$routingKey"
    }
}