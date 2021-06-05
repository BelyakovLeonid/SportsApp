package com.modo.modo.sportsapp.events.allevents.di

import com.modo.modo.sportsapp.events.allevents.presentation.EventsAndActivitiesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun allEventsModule() = module {

    viewModel {
        EventsAndActivitiesViewModel(get())
    }
}