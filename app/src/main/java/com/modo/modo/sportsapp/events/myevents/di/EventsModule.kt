package com.modo.modo.sportsapp.events.myevents.di

import com.modo.modo.sportsapp.events.myevents.data.EventsApi
import com.modo.modo.sportsapp.events.myevents.data.EventsRepository
import com.modo.modo.sportsapp.events.myevents.presentation.MyEventsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

fun myEventsModule() = module {
    factory<EventsApi> { get<Retrofit>().create() }
    single { EventsRepository(get()) }

    viewModel {
        MyEventsViewModel(get())
    }
}