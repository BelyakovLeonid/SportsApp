package com.modo.modo.sportsapp.base.events.detail.di

import com.modo.modo.sportsapp.base.events.detail.presentation.EventDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun eventsModule() = module {

    viewModel { (eventId: String) ->
        EventDetailViewModel(get(), eventId)
    }
}