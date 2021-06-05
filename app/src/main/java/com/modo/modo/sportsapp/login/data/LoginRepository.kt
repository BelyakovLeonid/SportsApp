package com.modo.modo.sportsapp.login.data

import android.util.Log
import com.modo.modo.sportsapp.login.data.local.TokenRepository
import com.modo.modo.sportsapp.login.data.remote.LoginApi
import com.modo.modo.sportsapp.login.data.remote.model.LoginRequest

class LoginRepository(
    private val api: LoginApi,
    private val tokenRepository: TokenRepository
) {
    suspend fun doLogin(login: String, password: String): Boolean {
        val response = api.login(LoginRequest(login, password))
        response.token?.let { tokenRepository.saveToken(it) }
        response.employeeCode?.let { tokenRepository.saveUserId(it) }
        return response.token.isNullOrEmpty().not()
    }
}