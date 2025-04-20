package com.example.player.ui.searchscreen.state

internal sealed interface SearchTrackUiEvent {
    data class InputQuery(val query: String) : SearchTrackUiEvent
    data object ChartState : SearchTrackUiEvent
}