package com.modo.modo.sportsapp.admin.camera.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.barcode.Barcode
import com.modo.modo.sportsapp.admin.camera.data.UserDetectionRepository
import com.modo.modo.sportsapp.admin.camera.presentation.model.PersonUiModel
import com.modo.modo.sportsapp.base.events.myevents.presentation.model.ParticipantStatus
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class CameraViewModel(
    private val userDetectionRepository: UserDetectionRepository
) : ViewModel() {

    private val _personFound = MutableStateFlow<PersonUiModel?>(null)
    val personFound = _personFound.asStateFlow()

    fun onBarcodeFind(barcode: Barcode) {
        viewModelScope.launch(CoroutineExceptionHandler { _, _ ->
            _personFound.value = null
        }) {
            val detectedId = userDetectionRepository.sendQrCode(barcode.displayValue ?: "") ?: throw IOException()
            val person = userDetectionRepository.getUser(detectedId) ?: throw IOException()
            _personFound.value = PersonUiModel(
                "${person.firstName} ${person.middleName} ${person.lastName}",
                person.imageUrl,
                ParticipantStatus.getStateFromString(person.role)
            )
        }
    }
}