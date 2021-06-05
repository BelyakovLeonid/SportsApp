package com.modo.modo.sportsapp._temp.user.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val employeeCode: String,
    val firstName: String,
    val imageUrl: String,
    val lastName: String,
    val middleName: String,
    val role: String
)