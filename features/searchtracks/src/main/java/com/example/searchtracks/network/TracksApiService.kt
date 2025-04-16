package com.example.searchtracks.network

import com.example.searchtracks.data.dto.TracksResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

internal interface TracksApiService {

    @GET("search")
    suspend fun getTracks(@Query("q") text: String): TracksResponseDto
}