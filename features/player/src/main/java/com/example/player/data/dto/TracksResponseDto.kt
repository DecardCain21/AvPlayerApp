package com.example.player.data.dto

import com.google.gson.annotations.SerializedName

internal class TracksResponseDto(
    @SerializedName("data") val data: List<TrackDto>,
    @SerializedName("total") val total: Int,
    @SerializedName("next") val next: String?
)