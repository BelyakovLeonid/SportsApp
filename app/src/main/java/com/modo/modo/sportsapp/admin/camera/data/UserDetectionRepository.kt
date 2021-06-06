package com.modo.modo.sportsapp.admin.camera.data

import com.modo.modo.sportsapp.admin.camera.data.model.UserDto

class UserDetectionRepository(
    private val api: UserDetectionApi
) {

    suspend fun sendQrCode(qrString: String): String? {
        return api.sendQrCode(qrString).userId
    }

    suspend fun getUser(userId: String): UserDto? {
        return api.getUser(userId)
    }
}