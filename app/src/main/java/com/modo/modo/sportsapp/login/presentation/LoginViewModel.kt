package com.modo.modo.sportsapp.login.presentation

import android.app.Application
import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.modo.modo.sportsapp.login.data.LoginRepository
import com.modo.modo.sportsapp.login.domain.AuthResponse
import com.modo.modo.sportsapp.model.domain.common.DataWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filterNotNull

class LoginViewModel constructor(@NonNull application: Application): AndroidViewModel(application) {

    private var repository = LoginRepository()

    private val _navigationCommands = MutableSharedFlow<Int?>(replay = 0)
    val navigationCommands: Flow<Int> = _navigationCommands.filterNotNull()

    fun bindAuth(): LiveData<DataWrapper<AuthResponse?>?>? {
        Log.d(TAG, "bindAuth: ")
        return repository.mldAuthData
    }

    fun doLogin(login: String?, pass: String?) {
        Log.d(TAG, String.format("doLogin: login = %s, pass = %s", login, pass))
        repository.auth(login, pass)
    }

    companion object {
        const val TAG = "LoginViewModel"
    }
}