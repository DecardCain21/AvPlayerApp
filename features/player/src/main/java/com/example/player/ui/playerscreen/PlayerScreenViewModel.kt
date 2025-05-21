package com.example.player.ui.playerscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.example.core.network.NetworkError
import com.example.player.domain.usecases.GetTrackByIdUseCase
import com.example.player.ui.playernot.api.AvServiceProvider
import com.example.player.ui.playernot.service.AvMediaServiceHandler
import com.example.player.ui.playerscreen.state.PlayerScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

public class PlayerScreenViewModel(
    private val getTrackByIdUseCase: GetTrackByIdUseCase,
    private val handler: AvMediaServiceHandler,
    private val avServiceProvider: AvServiceProvider
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(PlayerScreenState(trackState = PlayerScreenState.TrackState.EmptyTrack))
    internal val uiState: StateFlow<PlayerScreenState> = _uiState.asStateFlow()

    public fun loadTrackById(trackId: Long) {
        viewModelScope.launch {
            val result = getTrackByIdUseCase(id = trackId)
            val newState = when (result.exceptionOrNull()) {
                is NetworkError.ServerError,
                is NetworkError.NoData,
                is NetworkError.NoInternet -> PlayerScreenState(
                    trackState = PlayerScreenState.TrackState.EmptyTrack
                )

                else -> result.getOrNull()?.let {
                    PlayerScreenState(
                        PlayerScreenState.TrackState.TrackData(it)
                    )
                }
            }
            if (newState != null) {
                _uiState.value = newState
            }
        }
    }

    public fun addMediaItemService(
        url: String,
        albumArtist: String,
        displayTitle: String,
        subTitle: String
    ) {
        handler.addMediaItem(
            MediaItem.Builder()
                .setUri(url)
                .setMediaMetadata(
                    MediaMetadata.Builder()
                        .setAlbumArtist(albumArtist)
                        .setDisplayTitle(displayTitle)
                        .setSubtitle(subTitle)
                        .build()
                )
                .build()
        )
        //avServiceProvider.startService()
    }
}