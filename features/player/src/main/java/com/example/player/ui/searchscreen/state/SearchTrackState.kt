package com.example.player.ui.searchscreen.state

import com.example.player.domain.models.Track


internal data class SearchTrackState(
    val inputValue: Input = Input.Query("Mick Gordon"),
    val list: Tracks
) {
    sealed interface Tracks {
        data class TracksData(val listTracks: List<Track>) : Tracks
        data object Loading : Tracks
        data object Empty : Tracks
    }

    sealed interface Input {
        data object Empty : Input
        data class Query(val text: String = "") : Input
    }

}
