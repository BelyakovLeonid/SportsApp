package com.modo.modo.sportsapp.base.events.allevents.di

import com.modo.modo.sportsapp.base.events.allevents.presentation.EventsAndActivitiesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun allEventsModule() = module {

    viewModel {
        EventsAndActivitiesViewModel(get())
    }
}