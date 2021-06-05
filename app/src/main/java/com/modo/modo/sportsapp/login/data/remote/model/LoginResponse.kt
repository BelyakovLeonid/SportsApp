package com.modo.modo.sportsapp.login.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val employeeCode: String? = null,
    val token: String? = null
)