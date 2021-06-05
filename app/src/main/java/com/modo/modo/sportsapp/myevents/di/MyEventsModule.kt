package com.modo.modo.sportsapp.myevents.di

import com.modo.modo.sportsapp.myevents.data.MyEventsApi
import com.modo.modo.sportsapp.myevents.data.MyEventsRepository
import com.modo.modo.sportsapp.myevents.presentation.MyEventsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

fun myEventsModule() = module {
    factory<MyEventsApi> { get<Retrofit>().create() }
    single { MyEventsRepository(get()) }

    viewModel {
        MyEventsViewModel(get())
    }
}