package com.example.player.data.network

import com.example.player.data.dto.TracksResponseDto
import com.example.player.data.dto.ChartResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

internal interface TracksApiService {

    @GET("search")
    suspend fun getTracks(@Query("q") text: String): TracksResponseDto

    @GET("chart")
    suspend fun getChart(): ChartResponseDto
}