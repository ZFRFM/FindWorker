package ru.faimizufarov.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

private const val BASE_URL = "https://drive.usercontent.google.com/"

private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(
            Json.asConverterFactory("application/json".toMediaType()),
        )
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()

object AppApi {
    val retrofitService: AppApiInterface by lazy {
        retrofit.create(AppApiInterface::class.java)
    }
}