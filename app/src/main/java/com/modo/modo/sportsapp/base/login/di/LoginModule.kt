package com.modo.modo.sportsapp.base.login.di

import com.modo.modo.sportsapp.user.interests.presentation.InterestsViewModel
import com.modo.modo.sportsapp.base.login.data.LoginFlowRepository
import com.modo.modo.sportsapp.base.login.data.LoginRepository
import com.modo.modo.sportsapp.base.login.data.remote.LoginApi
import com.modo.modo.sportsapp.base.login.presentation.LoginViewModel
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