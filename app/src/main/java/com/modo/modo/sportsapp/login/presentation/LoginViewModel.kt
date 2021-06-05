package com.modo.modo.sportsapp.login.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.login.data.LoginRepository
import com.modo.modo.sportsapp.login.data.local.TokenRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val tokenRepository: TokenRepository
) : ViewModel() {

    private val _navigationCommands = MutableStateFlow<Int?>(null)
    val navigationCommands: Flow<Int> = _navigationCommands.filterNotNull()

    private val _loginSuccess = MutableStateFlow<Boolean?>(null)
    val loginSuccess: Flow<Boolean> = _loginSuccess.filterNotNull()

    fun checkLoggedInState() {
        if (tokenRepository.getToken() != null) {
            _navigationCommands.tryEmit(R.id.tabsFragment)
        }
    }

    fun onLoginClick(login: String, pass: String) {
        viewModelScope.launch(CoroutineExceptionHandler(::onError)) {
            val isSuccess = loginRepository.doLogin(login, pass)
            _loginSuccess.value = isSuccess
            if (isSuccess) _navigationCommands.emit(R.id.interestsFragment)
        }
    }

    private fun onError(context: CoroutineContext, t: Throwable) {
        _loginSuccess.value = false
    }
}