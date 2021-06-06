package com.modo.modo.sportsapp.base.login.data.local

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

    fun getUserId(): String? {
        return localStorage.getString(USER_KEY, null)
    }

    fun saveUserId(user: String) {
        localStorage.edit().putString(USER_KEY, user).apply()
    }

    companion object {
        private const val TOKEN_KEY = "token"
        private const val USER_KEY = "user"
    }
}