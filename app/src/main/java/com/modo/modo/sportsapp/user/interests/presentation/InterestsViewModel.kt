package com.modo.modo.sportsapp.user.interests.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.modo.modo.sportsapp.base.login.data.LoginFlowRepository

class InterestsViewModel(
    private val repository: LoginFlowRepository
) : ViewModel() {

    fun onInterestsSelected(sportsmenInterests: List<String>, fanInterests: List<String>) {
        Log.d("MyTag", "sportsmenInterests $sportsmenInterests fanInterests $fanInterests")
        repository.setInterestsShown()
    }
}