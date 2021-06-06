package com.modo.modo.sportsapp.admin.camera.presentation.model

import android.os.Parcelable
import com.modo.modo.sportsapp.base.events.myevents.presentation.model.ParticipantStatus
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonUiModel(
    val name: String,
    val imageUrl: String,
    val status: ParticipantStatus
) : Parcelable