package com.modo.modo.sportsapp.base.events.myevents.di

import com.modo.modo.sportsapp.base.events.myevents.data.EventsApi
import com.modo.modo.sportsapp.base.events.myevents.data.EventsRepository
import com.modo.modo.sportsapp.base.events.myevents.presentation.MyEventsViewModel
import com.modo.modo.sportsapp.base.events.onlyevents.presentation.EventsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

fun myEventsModule() = module {
    factory<EventsApi> { get<Retrofit>().create() }
    single { EventsRepository(get(), get()) }

    viewModel { MyEventsViewModel(get()) }
    viewModel { EventsViewModel(get()) }
}