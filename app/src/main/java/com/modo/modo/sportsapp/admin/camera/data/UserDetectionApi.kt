package com.modo.modo.sportsapp.admin.camera.data

import com.modo.modo.sportsapp.admin.camera.data.model.UserDto
import com.modo.modo.sportsapp.base.events.myevents.data.remote.model.UserRegisteredDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserDetectionApi {

    @GET("qr/approve/{qrCodeKey}")
    suspend fun sendQrCode(
        @Path("qrCodeKey") qrCodeKey: String,
    ): UserRegisteredDto

    @GET("user/{userId}")
    suspend fun getUser(
        @Path("userId") userId: String,
    ): UserDto?
}
