package com.modo.modo.sportsapp.events.myevents.data

import com.modo.modo.sportsapp.events.myevents.data.model.Event
import com.modo.modo.sportsapp.events.myevents.data.remote.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EventsRepository(
    private val api: EventsApi
) {

    private var cachedEvents = MutableStateFlow<List<Event>>(emptyList())

    suspend fun loadEvents(): List<Event> {
        val events = api.getEvents()
        return events.map { it.toDomain() }.also {
            cachedEvents.value = it
        }
    }

    fun getEventsAsFlow(): Flow<List<Event>> {
        return cachedEvents.asStateFlow()
    }

    fun getEvent(eventId: String): Event? {
        return cachedEvents.value.find { it.id == eventId }
    }
}