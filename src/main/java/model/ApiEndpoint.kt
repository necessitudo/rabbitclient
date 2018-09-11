package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApiEndpoint {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("baseURL")
    @Expose
    var baseURL: String? = null
    @SerializedName("subscription")
    @Expose
    var subscription: String? = null
    @SerializedName("destination")
    @Expose
    var destination: String? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null


}

