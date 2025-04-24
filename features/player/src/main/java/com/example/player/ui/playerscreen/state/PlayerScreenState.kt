package com.example.player.ui.playerscreen.state

import com.example.player.domain.models.Track


internal data class PlayerScreenState(val trackState: TrackState) {
    sealed interface TrackState {
        data class TrackData(val track: Track) : TrackState
        data object EmptyTrack : TrackState
    }
}