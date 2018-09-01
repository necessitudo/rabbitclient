package data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Proxy {

    @SerializedName("apply")
    @Expose
    var apply: Boolean? = null
    @SerializedName("server")
    @Expose
    var server: String? = null
    @SerializedName("port")
    @Expose
    var port: Int? = null

}