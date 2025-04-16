package com.example.searchtracks.domain.api

import com.example.searchtracks.domain.models.Track

public interface GetDeezerTracksUseCase {
    public suspend operator fun invoke(query:String): Result<List<Track>>
}