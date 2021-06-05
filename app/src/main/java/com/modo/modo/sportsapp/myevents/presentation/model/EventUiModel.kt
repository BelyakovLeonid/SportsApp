package com.modo.modo.sportsapp.myevents.presentation.model

import com.modo.modo.sportsapp.base.utils.formatToString
import com.modo.modo.sportsapp.myevents.data.model.Event

data class EventUiModel(
    val id: String,
    val name: String,
    val address: String,
    val userStatus: ParticipantStatus,
    val date: String?,
    val isNearest: Boolean,
    val description: String,
    val isOpen: Boolean
)

fun Event.toUi(index: Int = 0): EventUiModel {
    return EventUiModel(
        id = id,
        name = name,
        address = address,
        userStatus = ParticipantStatus.SPORTSMEN,
        date = date?.formatToString(),
        isNearest = index < 2,
        description = "", //todo
        isOpen = index == 0
    )
}