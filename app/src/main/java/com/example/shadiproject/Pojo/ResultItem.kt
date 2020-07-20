package com.example.shadiproject.Pojo

import androidx.room.Entity

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ResultItem : Serializable {

    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @SerializedName("name")
    @Expose
    var name: Name? = null


    @SerializedName("login")
    @Expose
    var login: Login? = null


    @Expose
    var email: String? = null

    @SerializedName("location")
    @Expose
    var locations: Location? = null

    @SerializedName("dob")
    @Expose
    var dob: Dob? = null

    @SerializedName("phone")
    @Expose
    var phone: String? = null

    @SerializedName("cell")
    @Expose
    var cell: String? = null

    var id: Id? = null

    @SerializedName("picture")
    @Expose
    var picture: Picture? = null


    companion object {
        private const val serialVersionUID = 3931358415462998715L
    }

}
