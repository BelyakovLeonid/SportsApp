package com.modo.modo.sportsapp.interests.presentation

import android.util.Log
import androidx.lifecycle.ViewModel

class InterestsViewModel : ViewModel() {

    fun onInterestsSelected(sportsmenInterests: List<String>, fanInterests: List<String>) {
        Log.d("MyTag", "sportsmenInterests $sportsmenInterests fanInterests $fanInterests")
    }
}