package com.example.player.ui.searchscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.network.NetworkError
import com.example.core.utils.debounce
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
                if (event.query.isNotEmpty()) {
                    searchDebounceAction(event.query)
                } else {
                    _uiState.value = SearchTrackState(
                        inputValue = SearchTrackState.Input.Empty,
                        list = SearchTrackState.Tracks.Empty
                    )
                }
            }
        }

    }

    private val searchDebounceAction = debounce<String>(
        delayMillis = 300L,
        coroutineScope = viewModelScope,
        useLastParam = true
    ) { changedText ->
        searchTracks(changedText)
    }

    private fun searchTracks(query: String) {
        if (dublicate(query)) return
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
                }
            }

            if (newState != null) {
                _uiState.value = newState
            }

        }
    }

    private fun dublicate(query: String): Boolean {
        return (_uiState.value.inputValue as? SearchTrackState.Input.Query)?.text == query
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