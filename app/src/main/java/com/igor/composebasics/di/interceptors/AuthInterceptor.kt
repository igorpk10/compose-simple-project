package com.igor.composebasics.di.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.http2.Header

class AuthInterceptor(
    private val key: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", key)
            .removeHeader("User-Agent")
            .build()

        return chain.proceed(request)
    }
}