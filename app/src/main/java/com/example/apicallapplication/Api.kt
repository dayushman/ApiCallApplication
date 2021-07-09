package com.example.apicallapplication

import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface Api {

    @POST("v3/new-auth/doctor/login")
    fun authenticate(@Body userInfo : JSONObject) : Call<JSONObject>

    @POST("posts")
    fun posts(@Body userInfo : JSONObject) : Call<JSONObject>

    @GET("api/characters")
    fun getHarry() : Call<List<JSONObject>>
}