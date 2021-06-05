package com.modo.modo.sportsapp.login.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val employeeCode: String,
    val password: String
)
