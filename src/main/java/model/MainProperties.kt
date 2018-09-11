package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MainProperties {

    @SerializedName("host")
    @Expose
    var host: String? = null

    @SerializedName("vHost")
    @Expose
    var vHost: String? = null

    @SerializedName("port")
    @Expose
    var port: Int? = null

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null

}
