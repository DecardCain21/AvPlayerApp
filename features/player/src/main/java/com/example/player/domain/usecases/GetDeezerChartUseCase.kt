package com.example.player.domain.usecases

import com.example.player.domain.models.Track

public interface GetDeezerChartUseCase {
    public suspend operator fun invoke(): Result<List<Track>>
}