package com.example.shadiproject.Pojo

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Login : Serializable {

    @SerializedName("uuid")
    @Expose
    var uuid: String = "NA"
    @SerializedName("username")
    @Expose
    var username: String? = null
    @SerializedName("password")
    @Expose
    var password: String? = null
    @SerializedName("salt")
    @Expose
    var salt: String? = null
    @SerializedName("md5")
    @Expose
    var md5: String? = null
    @SerializedName("sha1")
    @Expose
    var sha1: String? = null
    @SerializedName("sha256")
    @Expose
    var sha256: String? = null

    companion object {
        private const val serialVersionUID = -7926607516592017593L
    }

}
