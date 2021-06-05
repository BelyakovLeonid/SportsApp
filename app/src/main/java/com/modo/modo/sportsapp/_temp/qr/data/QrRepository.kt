package com.modo.modo.sportsapp._temp.qr.data

import android.util.Log
import com.modo.modo.sportsapp._temp.qr.data.remote.QrApi

class QrRepository(
    private val api: QrApi
) {

    /**
     * Возвращает был ли одобрен QR код
     */
    suspend fun approveQr(qrCodeKey: String): Boolean {
        val response = api.approveQr(qrCodeKey)
        Log.d(TAG, "approveQr: response = $response")
        return response.approved
    }

    companion object {
        private const val TAG = "QrRepository"
    }
}