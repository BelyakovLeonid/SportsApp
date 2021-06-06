package com.modo.modo.sportsapp.login.di

import com.modo.modo.sportsapp.interests.presentation.InterestsViewModel
import com.modo.modo.sportsapp.login.data.LoginFlowRepository
import com.modo.modo.sportsapp.login.data.LoginRepository
import com.modo.modo.sportsapp.login.data.remote.LoginApi
import com.modo.modo.sportsapp.login.presentation.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

fun loginModule() = module {
    factory<LoginApi> { get<Retrofit>().create() }
    factory { LoginRepository(get(), get()) }
    factory { LoginFlowRepository(get()) }

    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { InterestsViewModel(get()) }
}