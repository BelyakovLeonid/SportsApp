package com.modo.modo.sportsapp.user.feature5.presentation

import androidx.lifecycle.ViewModel
import com.modo.modo.sportsapp.base.login.data.LoginFlowRepository
import com.modo.modo.sportsapp.base.login.data.local.TokenRepository

class ProfileViewModel(
    private val tokenRepository: TokenRepository,
    private val loginFlowRepository: LoginFlowRepository
) : ViewModel() {

    fun logout() {
        tokenRepository.clear()
        loginFlowRepository.clear()
    }
}