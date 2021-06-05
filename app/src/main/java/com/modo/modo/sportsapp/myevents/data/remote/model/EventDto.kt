package com.modo.modo.sportsapp.myevents.data.remote.model

import com.modo.modo.sportsapp.base.utils.toLocalDateTime
import com.modo.modo.sportsapp.myevents.data.model.Event
import com.modo.modo.sportsapp.myevents.presentation.model.ParticipantStatus
import kotlinx.serialization.Serializable
import java.time.format.DateTimeFormatter

@Serializable
data class EventDto(
    val address: String,
    val description: String,
    val date: String? = null,
    val id: String,
    val imageUrl: String,
    val limit: Int,
    val name: String,
    val registrationOver: Boolean,
    val approved: Boolean,
    val participantStatus: String
)

fun EventDto.toDomain(): Event {
    return Event(
        address = address,
        description = description,
        date = date?.toLocalDateTime(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
        id = id,
        imageUrl = imageUrl,
        name = name,
        isRegApproved = approved,
        participantStatus = ParticipantStatus.getStateFromString(participantStatus)
    )
}