package com.modo.modo.sportsapp.login.data.local

import android.content.SharedPreferences

class TokenRepository(
    private val localStorage: SharedPreferences
) {

    fun getToken(): String? {
        return localStorage.getString(TOKEN_KEY, null)
    }

    fun saveToken(token: String) {
        localStorage.edit().putString(TOKEN_KEY, token).apply()
    }

    companion object {
        private const val TOKEN_KEY = "token"
    }
}