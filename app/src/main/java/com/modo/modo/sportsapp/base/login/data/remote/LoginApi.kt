package com.modo.modo.sportsapp.base.login.data.remote

import com.modo.modo.sportsapp.base.login.data.remote.model.LoginRequest
import com.modo.modo.sportsapp.base.login.data.remote.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse
}