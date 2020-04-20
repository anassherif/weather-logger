package com.anas.weatherlogger.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This is our network class that uses Retrofit for server calls.
 */
class NetworkRetrofit {

    private val openWeatherBackendVersion = "2.5"
    private val openWeatherBaseUrl =
        "https://samples.openweathermap.org/data/$openWeatherBackendVersion/"

    /**
     * Create an instance of the desired API interface.
     *
     * @param cls to create an instance of it.
     *
     * @return an instance of type T.
     */
    fun <T> getInstance(cls: Class<T>): T {
        return getRetrofit().create(cls)
    }

    /**
     * @return an instance of Retrofit class.
     */
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(openWeatherBaseUrl)
            .addConverterFactory(getConverterFactory())
            .client(getClient())
            .build()
    }

    /**
     * @return The converter factory to be used in retrofit to parse the data.
     */
    private fun getConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }

    /**
     * @return The [OkHttpClient]
     */
    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(getHttpLoggingInterceptor())
            .addInterceptor(getHttpInterceptor())
            .build()
    }

    /**
     * @return The [HttpInterceptor] that is used to add a query param to every request sent.
     */
    private fun getHttpInterceptor(): HttpInterceptor {
        return HttpInterceptor()
    }

    /**
     * @return The [HttpInterceptor] that is used to Log the request and response.
     */
    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}