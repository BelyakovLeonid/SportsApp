package com.modo.modo.sportsapp.events.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val id: String,
    val name: String,
    val address: String,
    val eventDate: String,
    val imageUrl: String,
    val limit: Int,
    val registrationOver: Boolean
)
