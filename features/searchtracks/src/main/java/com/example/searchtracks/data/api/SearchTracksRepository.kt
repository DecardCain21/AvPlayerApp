package com.example.searchtracks.data.api

import com.example.core.domain.models.Track

public interface SearchTracksRepository {
    public suspend fun getTracks(query: String): Result<List<Track>>
}