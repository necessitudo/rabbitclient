package data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Main {

    @SerializedName("server")
    @Expose
    var server: String? = null
    @SerializedName("port")
    @Expose
    var port: Int? = null
    @SerializedName("login")
    @Expose
    var login: String? = null
    @SerializedName("password")
    @Expose
    var password: String? = null
    @SerializedName("proxy")
    @Expose
    var proxy: Proxy? = null

}
