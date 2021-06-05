package com.modo.modo.sportsapp._temp.activity.di

import com.modo.modo.sportsapp._temp.activity.data.ActivityRepository
import com.modo.modo.sportsapp._temp.activity.data.remote.ActivityApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

fun activityModule() = module {
    factory<ActivityApi> { get<Retrofit>().create() }
    factory { ActivityRepository(get()) }

    // viewModel { }
}