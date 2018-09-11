package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {

    @SerializedName("result")
    @Expose
    private var result: String? = null
    @SerializedName("items")
    @Expose
    private var items: List<Subscription>? = null

}