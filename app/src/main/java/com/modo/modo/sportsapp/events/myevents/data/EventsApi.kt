package com.modo.modo.sportsapp.events.myevents.data

import com.modo.modo.sportsapp.events.myevents.data.remote.model.EventDto
import retrofit2.http.GET

interface EventsApi {

    @GET("event/by-current-user")
    suspend fun getEvents(): List<EventDto>
}