package com.modo.modo.sportsapp.admin.camera.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val imageUrl: String,
    val role: String,
)