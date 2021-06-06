package com.modo.modo.sportsapp.base.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.base.login.data.LoginFlowRepository
import com.modo.modo.sportsapp.base.login.data.LoginRepository
import com.modo.modo.sportsapp.base.login.data.local.TokenRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val tokenRepository: TokenRepository,
    private val flowRepository: LoginFlowRepository
) : ViewModel() {

    private val _navigationCommands = MutableStateFlow<Int?>(null)
    val navigationCommands: Flow<Int> = _navigationCommands.filterNotNull()

    private val _loginSuccess = MutableStateFlow<Boolean?>(null)
    val loginSuccess: Flow<Boolean> = _loginSuccess.filterNotNull()

    //в данный момент вход под админом реализован таким образом
    private var onLogoClicked = 0

    fun checkLoggedInState() {
        if (tokenRepository.getToken() != null) {
            val destId = if (flowRepository.hasInterestsShown()) {
                R.id.tabsFragment
            } else {
                R.id.interestsFragment
            }
            _navigationCommands.tryEmit(destId)
        }
    }

    fun onLoginClick(login: String, pass: String) {
        viewModelScope.launch(CoroutineExceptionHandler(::onError)) {
            val isSuccess = loginRepository.doLogin(login, pass)
            _loginSuccess.value = isSuccess
            if (isSuccess) {
                val destId = when {
                    onLogoClicked > 5 -> R.id.eventsFragment
                    flowRepository.hasInterestsShown() -> R.id.tabsFragment
                    else -> R.id.interestsFragment
                }
                _navigationCommands.emit(destId)
            }
        }
    }

    fun onLogoClicked() {
        onLogoClicked++
    }

    private fun onError(context: CoroutineContext, t: Throwable) {
        _loginSuccess.value = false
    }
}