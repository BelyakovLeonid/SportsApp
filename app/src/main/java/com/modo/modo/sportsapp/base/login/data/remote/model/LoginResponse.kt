package com.modo.modo.sportsapp.base.login.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val employeeCode: String? = null,
    val token: String? = null,
    val id: String? = null
)