package com.modo.modo.sportsapp.myevents.data

import com.modo.modo.sportsapp.myevents.data.remote.model.EventDto
import retrofit2.http.GET

interface EventsApi {

    @GET("event/by-current-user")
    suspend fun getEvents(): List<EventDto>
}