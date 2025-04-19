package com.example.playback.domain.usecases

import com.example.core.domain.models.Track
import com.example.playback.data.api.PlaybackRepository
import com.example.playback.domain.api.GetTrackByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

public class GetTrackByIdUseCaseImpl(
    private val playbackRepository: PlaybackRepository
) : GetTrackByIdUseCase {
    override suspend fun invoke(id: Long): Result<Track> = withContext(Dispatchers.IO) {
        playbackRepository.getTrack(id = id)
    }
}