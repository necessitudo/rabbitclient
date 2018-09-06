package data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Config {

    @SerializedName("ApiEndpoints")
    @Expose
    var apiEndpoints: List<ApiEndpoint>? = null
    @SerializedName("Main")
    @Expose
    var main: MainProperties? = null

}