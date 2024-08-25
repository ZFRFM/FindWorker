package ru.faimizufarov.data.network

import retrofit2.http.GET
import ru.faimizufarov.data.models.Result

interface AppApiInterface {
    @GET("u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun getResult(): Result
}