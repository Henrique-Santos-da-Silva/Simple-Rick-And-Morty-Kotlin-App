package com.demo.android.henrique.rickandmotyapp.datasource.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpClient {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    private fun httpClient(): OkHttpClient {
        val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

    }

    fun retrofit(): ApiEndPoint = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient())
        .build()
        .create(ApiEndPoint::class.java)
}