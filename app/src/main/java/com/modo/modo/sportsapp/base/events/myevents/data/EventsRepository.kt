package com.modo.modo.sportsapp.base.events.myevents.data

import com.modo.modo.sportsapp.base.events.myevents.data.model.Event
import com.modo.modo.sportsapp.base.events.myevents.data.remote.model.UserRegisteredDto
import com.modo.modo.sportsapp.base.events.myevents.data.remote.model.toDomain
import com.modo.modo.sportsapp.base.events.myevents.presentation.model.ParticipantStatus
import com.modo.modo.sportsapp.base.login.data.local.TokenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EventsRepository(
    private val api: EventsApi,
    private val tokenRepository: TokenRepository
) {

    private var cachedEvents = MutableStateFlow<List<Event>>(emptyList())

    suspend fun loadEvents() {
        val events = api.getEvents()
        cachedEvents.value = events.map { it.toDomain() }
    }

    fun getEventsAsFlow(): Flow<List<Event>> {
        return cachedEvents.asStateFlow()
    }

    suspend fun setParticipateInEvent(eventId: String, participantStatus: ParticipantStatus) {
        val userId = tokenRepository.getUserId() ?: return
        api.setParticipateInEvent(
            UserRegisteredDto(
                approved = false,
                eventId = eventId,
                userId = userId,
                participationType = participantStatus.name,
            )
        )

        val updatedEvents = cachedEvents.value.toMutableList()
        updatedEvents.map {
            if (it.id == eventId) {
                it.copy(participantStatus = participantStatus)
            } else {
                it
            }
        }
        cachedEvents.value = updatedEvents
    }

    fun getEvent(eventId: String): Event? {
        return cachedEvents.value.find { it.id == eventId }
    }
}