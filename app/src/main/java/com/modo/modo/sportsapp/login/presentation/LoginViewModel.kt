package com.modo.modo.sportsapp.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modo.modo.sportsapp.R
import com.modo.modo.sportsapp.login.data.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val loginRepository = LoginRepository()

    private val _loginState = MutableStateFlow<Boolean?>(null)
    val loginState: Flow<Boolean> = _loginState.filterNotNull()

    private val _navigationCommands = MutableSharedFlow<Int?>(replay = 0)
    val navigationCommands: Flow<Int> = _navigationCommands.filterNotNull()

    fun onLoginClick(login: String, password: String) {
        viewModelScope.launch {
            val resultSuccess = loginRepository.doLogin(login, password)

            _loginState.value = resultSuccess
            if (resultSuccess) _navigationCommands.emit(R.id.interestsFragment)
        }
    }
}