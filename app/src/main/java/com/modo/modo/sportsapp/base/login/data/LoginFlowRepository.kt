package com.modo.modo.sportsapp.base.login.data

import android.content.SharedPreferences

class LoginFlowRepository(
    private val localStorage: SharedPreferences
) {

    fun hasInterestsShown(): Boolean {
        return localStorage.getBoolean(TOKEN_INTERESTS, false)
    }

    fun setInterestsShown() {
        localStorage.edit().putBoolean(TOKEN_INTERESTS, true).apply()
    }

    companion object {
        private const val TOKEN_INTERESTS = "interests"
    }
}