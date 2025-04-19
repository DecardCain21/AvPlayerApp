package com.example.player.domain.usecases

import com.example.player.domain.models.Track

public interface GetDeezerTracksUseCase {
    public suspend operator fun invoke(query:String): Result<List<Track>>
}