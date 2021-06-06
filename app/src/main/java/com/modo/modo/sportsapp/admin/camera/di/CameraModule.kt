package com.modo.modo.sportsapp.admin.camera.di

import com.modo.modo.sportsapp.admin.camera.presentation.CameraViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun cameraModule() = module {
    viewModel {
        CameraViewModel()
    }
}