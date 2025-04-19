package com.example.playback.network

import com.example.core.network.dto.TrackDto
import retrofit2.http.GET
import retrofit2.http.Path

public interface GetTrackByIdApiService {

    @GET("track/{id}")
    public suspend fun getTrackById(@Path("id") id: Long): TrackDto
}