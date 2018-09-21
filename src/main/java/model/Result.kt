package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {

    @SerializedName("result")
    @Expose
    private val result: String? = null
    @SerializedName("items")
    @Expose
    val items: List<Subscription>? = null

}