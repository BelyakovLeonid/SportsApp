package com.modo.modo.sportsapp.base.network

import com.modo.modo.sportsapp.login.data.local.TokenRepository
import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor constructor(
    private val tokenRepository: TokenRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val savedToken = tokenRepository.getToken()
        return if (savedToken.isNullOrEmpty()) {
            chain.proceed(chain.request())
        } else {
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", savedToken)
                .build()
            chain.proceed(newRequest)
        }
    }
}