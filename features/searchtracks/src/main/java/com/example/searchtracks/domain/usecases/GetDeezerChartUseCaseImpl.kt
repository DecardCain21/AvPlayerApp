package com.example.searchtracks.domain.usecases

import com.example.core.domain.models.Track
import com.example.searchtracks.data.api.SearchTracksRepository
import com.example.searchtracks.domain.api.GetDeezerChartUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

public class GetDeezerChartUseCaseImpl(private val tracksRepository: SearchTracksRepository) :
    GetDeezerChartUseCase {
    override suspend fun invoke(): Result<List<Track>> = withContext(Dispatchers.IO) {
        tracksRepository.getChart()
    }
}