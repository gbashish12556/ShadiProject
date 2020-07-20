package com.example.shadiproject

import com.example.shadiproject.Pojo.ShadiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("api/?results=10")
    fun getAllResponse(): Call<ShadiResponse>

}