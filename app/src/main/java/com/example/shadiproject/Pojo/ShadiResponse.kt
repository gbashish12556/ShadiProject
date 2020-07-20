package com.example.shadiproject.Pojo

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ShadiResponse : Serializable {

    @SerializedName("results")
    @Expose
    var results: List<ResultItem>? = null
    @SerializedName("info")
    @Expose
    var info: Info? = null

    companion object {
        private const val serialVersionUID = -9147556811191890746L
    }

}
