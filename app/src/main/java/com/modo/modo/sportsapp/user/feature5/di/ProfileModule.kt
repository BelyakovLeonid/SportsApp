package com.modo.modo.sportsapp.user.feature5.di

import com.modo.modo.sportsapp.user.feature5.presentation.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun profileModule() = module {

    viewModel {
        ProfileViewModel(get(), get())
    }
}