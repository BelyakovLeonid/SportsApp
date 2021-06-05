package com.modo.modo.sportsapp._temp.activity.data.remote

import com.modo.modo.sportsapp._temp.activity.data.remote.model.ActivityDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ActivityApi {

    @GET("activity")
    suspend fun getActivities(): List<ActivityDto>

    @POST("activity")
    suspend fun createActivity(@Body activity: ActivityDto): ActivityDto

    @GET("activity/user/{userId}")
    suspend fun getUserActivities(@Path("userId") userId: String): List<ActivityDto>

}