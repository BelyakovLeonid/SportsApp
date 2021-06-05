package com.modo.modo.sportsapp._temp.user.di

import com.modo.modo.sportsapp._temp.user.data.UserRepository
import com.modo.modo.sportsapp._temp.user.data.remote.UserApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

fun userModule() = module {
    factory<UserApi> { get<Retrofit>().create() }
    factory { UserRepository(get()) }

    // viewModel { }
}