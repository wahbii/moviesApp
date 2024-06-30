package com.main.moviesApp.data.source.network.response

import com.main.moviesApp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response



class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val urlWithApiKey = originalRequest.url
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.APIKEY)
            .build()
        val newRequest = originalRequest
            .newBuilder()
            .url(urlWithApiKey)
            .build()
        return chain.proceed(newRequest)
    }
}