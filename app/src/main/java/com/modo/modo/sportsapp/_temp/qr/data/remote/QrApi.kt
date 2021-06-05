package com.modo.modo.sportsapp._temp.qr.data.remote

import com.modo.modo.sportsapp._temp.qr.data.remote.model.QrResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface QrApi {

    @GET("qr/approve/{qrCodeKey")
    suspend fun approveQr(
        @Path("qrCodeKey") qrCodeKey: String
    ): QrResponse
}