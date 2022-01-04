package com.example.gdsc_hackathon.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface QuoteApi {
    @GET("random?&maxLen=$length")
    fun getQuotes(): Call<JsonObject>

    companion object {
        var BASE_URL = "https://api.quotable.io/"
const val length: String ="150"


        fun create() : QuoteApi{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(QuoteApi::class.java)
        }
    }
}
