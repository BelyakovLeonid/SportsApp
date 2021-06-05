package com.modo.modo.sportsapp.events.myevents.data.model

import com.modo.modo.sportsapp.events.myevents.presentation.model.ParticipantStatus
import java.time.LocalDateTime

data class Event(
    val address: String,
    val description: String,
    val date: LocalDateTime?,
    val id: String,
    val imageUrl: String,
    val name: String,
    val isRegApproved: Boolean,
    val participantStatus: ParticipantStatus
)
