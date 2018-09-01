package data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApiEndpoint {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("subscription")
    @Expose
    var subscription: String? = null
    @SerializedName("login")
    @Expose
    var login: String? = null
    @SerializedName("password")
    @Expose
    var password: String? = null
    @SerializedName("destination")
    @Expose
    var destination: String? = null

}

