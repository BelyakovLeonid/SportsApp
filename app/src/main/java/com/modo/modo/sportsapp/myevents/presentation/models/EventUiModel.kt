package com.modo.modo.sportsapp.myevents.presentation.models

data class EventUiModel(
    val id: String,
    val name: String,
    val place: String,
    val userStatus: ParticipantStatus,
    val date: String,
    val isNearest: Boolean,
    val isOpen: Boolean
)
