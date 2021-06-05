package com.modo.modo.sportsapp.qr.di

import com.modo.modo.sportsapp.qr.presentation.QrViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun qrModule() = module {

    viewModel { (eventId: String) ->
        QrViewModel(get(), eventId)
    }
}