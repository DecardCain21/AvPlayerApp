package com.example.player.data.dto

import com.google.gson.annotations.SerializedName

internal data class ChartResponseDto(
    @SerializedName("tracks")
    val tracks: TracksDataDto
)

internal data class TracksDataDto(
    @SerializedName("data")
    val data: List<TrackDto>
)
