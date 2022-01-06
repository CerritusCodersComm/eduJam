package com.example.gdsc_hackathon.dao

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NotificationInterface {
    @POST("sendNotification")
    suspend fun createPost(@Body requestBody: RequestBody): Response<ResponseBody>
}