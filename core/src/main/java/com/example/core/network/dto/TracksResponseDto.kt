package com.example.core.network.dto

public class TracksResponseDto(
    public val data: List<TrackDto>,
    public val total: Int,
    public val next: String?
)