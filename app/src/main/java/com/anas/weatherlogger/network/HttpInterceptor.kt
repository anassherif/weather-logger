package com.anas.weatherlogger.network

import com.anas.weatherlogger.utils.constants.Constants
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Custom interceptor that is used to add the (appid) query parameter to every request instead of
 * adding it to every request method.
 */
class HttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val httpUrl = request.url()

        val newHttpUrl = httpUrl.newBuilder()
            .addQueryParameter(Constants.API_ID, Constants.API_KEY)
            .build()

        val newRequest = request.newBuilder()
            .url(newHttpUrl)
            .build()

        return chain.proceed(newRequest)
    }
}