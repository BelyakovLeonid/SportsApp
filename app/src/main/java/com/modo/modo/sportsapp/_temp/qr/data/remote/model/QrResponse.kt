package com.modo.modo.sportsapp._temp.qr.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class QrResponse(
    val approved: Boolean,
    val eventId: String,
    val participationType: String,
    val userId: String
)
