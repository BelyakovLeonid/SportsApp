package com.modo.modo.sportsapp.myevents.presentation.model

import com.modo.modo.sportsapp.base.utils.formatToString
import com.modo.modo.sportsapp.myevents.data.model.Event

sealed class EventItem(
    open val isNearest: Boolean
) {

    data class EventUiModel(
        val id: String,
        val name: String,
        val address: String,
        val userStatus: ParticipantStatus,
        val date: String?,
        val imageUrl: String,
        val description: String,
        val isOpen: Boolean,
        override val isNearest: Boolean
    ) : EventItem(isNearest)

    data class EmptyEventUiModel(
        override val isNearest: Boolean
    ) : EventItem(isNearest)
}

fun Event.toUi(index: Int = 0): EventItem.EventUiModel {
    return EventItem.EventUiModel(
        id = id,
        name = name,
        address = address,
        imageUrl = imageUrl,
        userStatus = ParticipantStatus.SPORTSMEN,
        date = date?.formatToString(),
        isNearest = index < 2,
        description = "", //todo
        isOpen = index == 0
    )
}