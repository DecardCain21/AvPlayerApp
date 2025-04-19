package com.example.player.domain.usecases.impl

import com.example.player.domain.models.Track
import com.example.player.data.api.SearchTracksRepository
import com.example.player.domain.usecases.GetDeezerChartUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class GetDeezerChartUseCaseImpl(private val tracksRepository: SearchTracksRepository) :
    GetDeezerChartUseCase {
    override suspend fun invoke(): Result<List<Track>> = withContext(Dispatchers.IO) {
        tracksRepository.getChart()
    }
}