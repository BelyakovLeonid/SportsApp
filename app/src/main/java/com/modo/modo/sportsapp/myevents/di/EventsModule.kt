package com.modo.modo.sportsapp.myevents.di

import com.modo.modo.sportsapp.myevents.data.EventsApi
import com.modo.modo.sportsapp.myevents.data.EventsRepository
import com.modo.modo.sportsapp.myevents.presentation.MyEventsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

fun eventsModule() = module {
    factory<EventsApi> { get<Retrofit>().create() }
    single { EventsRepository(get()) }

    viewModel {
        MyEventsViewModel(get())
    }
}