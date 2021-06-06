package com.modo.modo.sportsapp.events.myevents.data

import com.modo.modo.sportsapp.events.myevents.data.remote.model.EventDto
import com.modo.modo.sportsapp.events.myevents.data.remote.model.UserRegisteredDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventsApi {

    @GET("event/by-current-user")
    suspend fun getEvents(): List<EventDto>

    @POST("user-event")
    suspend fun setParticipateInEvent(
        @Body body: UserRegisteredDto
    ): UserRegisteredDto
}