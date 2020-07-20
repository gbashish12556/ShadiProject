package com.example.shadiproject.Pojo

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Info : Serializable {

    @SerializedName("seed")
    @Expose
    var seed: String? = null
    @SerializedName("results")
    @Expose
    var results: Int = 0
    @SerializedName("page")
    @Expose
    var page: Int = 0
    @SerializedName("version")
    @Expose
    var version: String? = null

    companion object {
        private const val serialVersionUID = 564430200589695037L
    }

}
