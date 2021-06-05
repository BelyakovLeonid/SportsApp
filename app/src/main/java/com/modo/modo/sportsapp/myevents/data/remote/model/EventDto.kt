package com.modo.modo.sportsapp.myevents.data.remote.model

import com.google.android.datatransport.cct.StringMerger
import com.modo.modo.sportsapp.base.utils.toLocalDateTime
import com.modo.modo.sportsapp.myevents.data.model.Event
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
    val registrationOver: Boolean
)

fun EventDto.toDomain(): Event {
    return Event(
        address = address,
        description = description,
        date = date?.toLocalDateTime(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
        id = id,
        imageUrl = imageUrl,
        name = name,
    )

}