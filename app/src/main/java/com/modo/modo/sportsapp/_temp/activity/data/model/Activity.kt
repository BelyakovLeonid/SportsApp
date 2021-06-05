package com.modo.modo.sportsapp._temp.activity.data.model

import java.time.LocalDateTime

data class Activity(
    val id: String,
    val activityDate: LocalDateTime?,
    val description: String,
    val imageUrl: String,
    val type: String
)
