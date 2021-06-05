package com.modo.modo.sportsapp.myevents.data.model

import java.time.LocalDateTime

data class Event(
    val address: String,
    val description: String,
    val date: LocalDateTime?,
    val id: String,
    val imageUrl: String,
    val name: String
)
