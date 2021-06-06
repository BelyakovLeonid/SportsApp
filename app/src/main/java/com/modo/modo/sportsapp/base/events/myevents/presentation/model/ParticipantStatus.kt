package com.modo.modo.sportsapp.base.events.myevents.presentation.model

enum class ParticipantStatus(val str: String?) {
    SPORTSMEN("SPORTSMAN"),
    FAN("FAN"),
    NONE("NONE");

    companion object {
        fun getStateFromString(str: String?): ParticipantStatus {
            return values().firstOrNull { it.str == str } ?: NONE
        }
    }
}