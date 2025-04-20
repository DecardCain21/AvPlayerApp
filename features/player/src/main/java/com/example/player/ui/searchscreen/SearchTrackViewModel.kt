package com.example.player.ui.searchscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.network.NetworkError
import com.example.player.domain.usecases.GetDeezerChartUseCase
import com.example.player.domain.usecases.GetDeezerTracksUseCase
import com.example.player.ui.searchscreen.state.SearchTrackState
import com.example.player.ui.searchscreen.state.SearchTrackUiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

public class SearchTrackViewModel(
    private val getDeezerTracksUseCase: GetDeezerTracksUseCase,
    private val getDeezerChartUseCase: GetDeezerChartUseCase
) : ViewModel() {

    init {
        loadTracks()
    }

    private val _uiState = MutableStateFlow(
        SearchTrackState(
            inputValue = SearchTrackState.Input.Empty,
            list = SearchTrackState.Tracks.Loading
        )
    )
    internal val uiState: StateFlow<SearchTrackState> = _uiState.asStateFlow()

    internal fun handleEventSearchTrackScreen(event: SearchTrackUiEvent) {
        when (event) {
            SearchTrackUiEvent.ChartState -> loadTracks()
            is SearchTrackUiEvent.InputQuery -> {
                if (event.query.isNotEmpty()) searchTracks(event.query)
            }
        }

    }

    private fun searchTracks(query: String) {
        viewModelScope.launch {
            val result = getDeezerTracksUseCase(query = query)
            val newState = when (result.exceptionOrNull()) {
                is NetworkError.ServerError,
                is NetworkError.NoData,
                is NetworkError.NoInternet -> SearchTrackState(
                    inputValue = SearchTrackState.Input.Empty,
                    list = SearchTrackState.Tracks.Empty
                )

                else -> result.getOrNull()?.let {
                    SearchTrackState(
                        inputValue = SearchTrackState.Input.Query(text = query),
                        list = SearchTrackState.Tracks.TracksData(listTracks = it)
                    )
                } ?: SearchTrackState(
                    inputValue = SearchTrackState.Input.Empty,
                    list = SearchTrackState.Tracks.Empty
                )
            }
            _uiState.value = uiState.value.copy(
                inputValue = SearchTrackState.Input.Query(text = query),
                list = newState.list
            )
        }
    }

    private fun loadTracks() {
        viewModelScope.launch {
            val result = getDeezerChartUseCase()
            val newState = when (result.exceptionOrNull()) {
                is NetworkError.ServerError,
                is NetworkError.NoData,
                is NetworkError.NoInternet -> SearchTrackState(
                    inputValue = SearchTrackState.Input.Empty,
                    list = SearchTrackState.Tracks.Empty
                )

                else -> result.getOrNull()?.let {
                    SearchTrackState(
                        inputValue = SearchTrackState.Input.Empty,
                        list = SearchTrackState.Tracks.TracksData(listTracks = it)
                    )
                } ?: SearchTrackState(
                    inputValue = SearchTrackState.Input.Empty,
                    list = SearchTrackState.Tracks.Empty
                )
            }
            _uiState.value = newState
        }
    }

}