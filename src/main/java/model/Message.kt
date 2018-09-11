package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Message(routingKey:String, payload:String) {


    @SerializedName("routingKey")
    @Expose
    private var routingKey: String? = null

    @SerializedName("payload")
    @Expose
    private var payload: String? = null

    @SerializedName("payload_encoding")
    @Expose
    private var payloadEncoding: String? = null

    init {
        this.routingKey = routingKey
        this.payload    = payload

    }
}