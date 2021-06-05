package com.modo.modo.sportsapp.base.di

import android.content.Context
import android.content.SharedPreferences
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.modo.modo.sportsapp.BuildConfig
import com.modo.modo.sportsapp.base.network.AuthTokenInterceptor
import com.modo.modo.sportsapp.login.data.local.TokenRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

fun baseModule() = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://sportevents-motomoto-devhack.herokuapp.com/api/v1/")
            .client(get())
            .addConverterFactory(get())
            .build()
    }

    factory {
        Json { ignoreUnknownKeys = true }
            .asConverterFactory("application/json".toMediaType())
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthTokenInterceptor>())
            .build()
    }

    factory { AuthTokenInterceptor(get()) }

    factory { TokenRepository(get()) }

    factory<SharedPreferences> {
        get<Context>().getSharedPreferences(
            "APP_PREFERENCES",
            Context.MODE_PRIVATE
        )
    }
}