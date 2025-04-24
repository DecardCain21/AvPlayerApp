package com.example.player.ui.playerscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.network.NetworkError
import com.example.player.domain.usecases.GetTrackByIdUseCase
import com.example.player.ui.playerscreen.state.PlayerScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

public class PlayerScreenViewModel(
    private val getTrackByIdUseCase: GetTrackByIdUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(PlayerScreenState(trackState = PlayerScreenState.TrackState.EmptyTrack))
    internal val uiState: StateFlow<PlayerScreenState> = _uiState.asStateFlow()

    public/*private*/ fun loadTrackById(trackId: Long) {
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
}