package com.example.player.data.network

import com.example.player.data.dto.TrackDto
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GetTrackByIdApiService {

    @GET("track/{id}")
    suspend fun getTrackById(@Path("id") id: Long): TrackDto
}