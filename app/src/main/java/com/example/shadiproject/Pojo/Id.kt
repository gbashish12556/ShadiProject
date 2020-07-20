package com.example.shadiproject.Pojo

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Id : Serializable {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("value")
    @Expose
    var value: Any? = null

    companion object {
        private const val serialVersionUID = 8540320461412808617L
    }

}
