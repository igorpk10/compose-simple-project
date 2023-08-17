package com.igor.composebasics.di.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val key: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestUrl = request.url

        val newUrl = requestUrl.newBuilder()
            .addQueryParameter("Authorization", key)
            .build()

        return chain.proceed(
            request.newBuilder().url(newUrl).build()
        )
    }
}