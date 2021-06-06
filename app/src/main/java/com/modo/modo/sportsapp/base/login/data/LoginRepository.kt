package com.modo.modo.sportsapp.base.login.data

import com.modo.modo.sportsapp.base.login.data.local.TokenRepository
import com.modo.modo.sportsapp.base.login.data.remote.LoginApi
import com.modo.modo.sportsapp.base.login.data.remote.model.LoginRequest

class LoginRepository(
    private val api: LoginApi,
    private val tokenRepository: TokenRepository
) {
    suspend fun doLogin(login: String, password: String): Boolean {
        val response = api.login(LoginRequest(login, password))
        response.token?.let { tokenRepository.saveToken(it) }
        response.id?.let { tokenRepository.saveUserId(it) }
        return response.token.isNullOrEmpty().not()
    }
}