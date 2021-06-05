package com.modo.modo.sportsapp._temp.user.data.remote

import com.modo.modo.sportsapp._temp.user.data.remote.model.EventRegistration
import com.modo.modo.sportsapp._temp.user.data.remote.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @GET("user")
    suspend fun getAllUsers(): List<User>

    @GET("user/{id}")
    suspend fun getUserById(@Path("id") id: String): User

    @GET("user-event/{id}")
    suspend fun getUserEventsById(@Path("id") id: String): List<EventRegistration>

    @POST("user-event")
    suspend fun registerUserToEvent(@Body eventRegistration: EventRegistration): EventRegistration
}