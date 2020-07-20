package com.example.shadiproject.Pojo

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Location : Serializable {

    @Expose
    var city: String? = null

    @SerializedName("state")
    @Expose
    var state: String? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("postcode")
    @Expose
    var postcode: Int = 0

    companion object {
        private const val serialVersionUID = 7917809462877486534L
    }

}
