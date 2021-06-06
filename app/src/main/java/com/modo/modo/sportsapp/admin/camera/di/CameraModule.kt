package com.modo.modo.sportsapp.admin.camera.di

import com.modo.modo.sportsapp.admin.camera.data.UserDetectionApi
import com.modo.modo.sportsapp.admin.camera.data.UserDetectionRepository
import com.modo.modo.sportsapp.admin.camera.presentation.CameraViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

fun cameraModule() = module {
    factory<UserDetectionApi> { get<Retrofit>().create() }
    factory { UserDetectionRepository(get()) }

    viewModel {
        CameraViewModel(get())
    }
}