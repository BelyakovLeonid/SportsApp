package com.modo.modo.sportsapp.myevents.data

import com.modo.modo.sportsapp.myevents.data.model.Event
import com.modo.modo.sportsapp.myevents.data.remote.model.toDomain

class EventsRepository(
    private val api: EventsApi
) {

    private var cachedEvents = emptyList<Event>()

    suspend fun loadEvents(): List<Event> {
        val events = api.getEvents()
        return events.map { it.toDomain() }.also {
            cachedEvents = it
        }
    }

    fun getEvent(eventId: String): Event? {
        return cachedEvents.find { it.id == eventId }
    }
}