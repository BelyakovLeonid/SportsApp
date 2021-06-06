package com.modo.modo.sportsapp.events.myevents.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisteredDto(
    val approved: Boolean,
    val eventId: String,
    val userId: String,
    val participationType: String
)