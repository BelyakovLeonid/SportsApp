package com.modo.modo.sportsapp._temp.user.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class EventRegistration(
    val approved: Boolean,
    val eventId: String,
    val userId: String,
    val participationType: String
)
