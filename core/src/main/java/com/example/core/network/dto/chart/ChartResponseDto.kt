package com.example.core.network.dto.chart

import com.example.core.network.dto.TrackDto

public data class ChartResponseDto(
    val tracks: TracksDataDto
)

public data class TracksDataDto(
    val data: List<TrackDto>
)
