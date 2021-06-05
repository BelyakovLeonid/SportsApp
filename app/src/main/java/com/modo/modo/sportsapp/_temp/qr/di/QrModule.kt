package com.modo.modo.sportsapp._temp.qr.di

import com.modo.modo.sportsapp._temp.qr.data.QrRepository
import com.modo.modo.sportsapp._temp.qr.data.remote.QrApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

fun qrModule() = module {
    factory<QrApi> { get<Retrofit>().create() }
    factory { QrRepository(get()) }

    // viewModel { }
}