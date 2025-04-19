package com.example.player.domain.usecases.impl

import com.example.player.data.api.SearchTracksRepository
import com.example.player.domain.usecases.GetDeezerTracksUseCase
import com.example.player.domain.models.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class GetDeezerTracksUseCaseImpl(
    private val tracksRepository: SearchTracksRepository
) : GetDeezerTracksUseCase {
    override suspend fun invoke(query: String): Result<List<Track>> = withContext(Dispatchers.IO) {
        tracksRepository.getTracks(query = query)
    }
}