package com.example.searchtracks.domain.usecases

import com.example.searchtracks.data.api.SearchTracksRepository
import com.example.searchtracks.domain.api.GetDeezerTracksUseCase
import com.example.core.domain.models.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

public class GetDeezerTracksUseCaseImpl(
    private val tracksRepository: SearchTracksRepository
) :
    GetDeezerTracksUseCase {
    override suspend fun invoke(query: String): Result<List<Track>> = withContext(Dispatchers.IO) {
        tracksRepository.getTracks(query = query)
    }
}