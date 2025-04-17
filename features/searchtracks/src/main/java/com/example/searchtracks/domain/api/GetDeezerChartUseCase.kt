package com.example.searchtracks.domain.api

import com.example.core.domain.models.Track

public interface GetDeezerChartUseCase {
    public suspend operator fun invoke(): Result<List<Track>>
}