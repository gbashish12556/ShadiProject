package com.example.shadiproject.Pojo

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Picture : Serializable {

    @SerializedName("large")
    @Expose
    var large: String? = null
    @SerializedName("medium")
    @Expose
    var medium: String? = null
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null

    companion object {
        private const val serialVersionUID = 7557716149440824361L
    }

}
