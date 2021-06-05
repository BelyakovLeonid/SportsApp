package com.modo.modo.sportsapp.events.di

import com.modo.modo.sportsapp.events.data.EventsRepository
import com.modo.modo.sportsapp.events.data.remote.EventsApi
import com.modo.modo.sportsapp.events.presentation.EventDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

fun eventsModule() = module {
    factory<EventsApi> { get<Retrofit>().create() }
    factory { EventsRepository(get()) }

    viewModel { EventDetailViewModel(get(), get()) }
}