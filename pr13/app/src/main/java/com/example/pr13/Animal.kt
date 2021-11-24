package com.example.pr13

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class Animal (
    @SerializedName("type")
    @Expose
    val type : String? = null,

    @SerializedName("text")
    @Expose
    val text : String? = null
)



interface ApiService {
    @GET("facts?")
    fun getSchedule(@Query("animal_type") animal : String) : Call<List<Animal>>
}