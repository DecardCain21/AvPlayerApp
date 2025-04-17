package com.example.core.network.dto

import com.google.gson.annotations.SerializedName

public class TracksResponseDto(
    @SerializedName("data")
    public val data: List<TrackDto>,
    @SerializedName("total")
    public val total: Int,
    @SerializedName("next")
    public val next: String?
)