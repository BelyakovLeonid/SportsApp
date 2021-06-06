package com.modo.modo.sportsapp.admin.camera.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.barcode.Barcode
import com.modo.modo.sportsapp.admin.camera.presentation.model.PersonUiModel
import com.modo.modo.sportsapp.base.events.myevents.presentation.model.ParticipantStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class CameraViewModel : ViewModel() {

    private val _personFound = MutableStateFlow<PersonUiModel?>(null)
    val personFound = _personFound.filterNotNull()

    fun onBarcodeFind(barcode: Barcode) {
        Log.d("MyTag", "onBarcodeFind = ${barcode.displayValue}")
        _personFound.value = PersonUiModel(
            "Зайцев Мазай Петрович",
            "https://m.media-amazon.com/images/M/MV5BMTI3MDc4NzUyMV5BMl5BanBnXkFtZTcwMTQyMTc5MQ@@._V1_.jpg",
            ParticipantStatus.FAN
        )
    }
}