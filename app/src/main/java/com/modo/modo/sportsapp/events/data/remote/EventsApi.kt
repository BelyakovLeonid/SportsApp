package com.modo.modo.sportsapp.events.data.remote

import com.modo.modo.sportsapp.events.data.remote.model.Event
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventsApi {

    @GET("event")
    suspend fun getEvents(): List<Event>

    @POST("event")
    suspend fun createEvent(@Body request: Event): Event
}