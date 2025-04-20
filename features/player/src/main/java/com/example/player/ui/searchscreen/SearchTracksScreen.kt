package com.example.player.ui.searchscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.player.domain.models.Track
import com.example.player.ui.composable.AvPlayerTextField
import com.example.player.ui.composable.AvPlayerTrackItem
import com.example.player.ui.searchscreen.state.SearchTrackState
import com.example.player.ui.searchscreen.state.SearchTrackUiEvent
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
public fun SearchTracksScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchTrackViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    var currentQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "SearchTracksScreen"
        )
        LaunchedEffect(currentQuery) {
            if (currentQuery.isNotEmpty()) {
                delay(300)
                viewModel.handleEventSearchTrackScreen(SearchTrackUiEvent.InputQuery(query = currentQuery))
            }
        }

        AvPlayerTextField(
            value = when (val currentStateInput = state.inputValue) {
                is SearchTrackState.Input.Empty -> ""
                is SearchTrackState.Input.Query -> currentStateInput.text
            },
            onValueChange = { currentQuery = it
                /*viewModel.handleEventSearchTrackScreen(
                    SearchTrackUiEvent.InputQuery(
                        query = it
                    )
                )*/
            }
        )

        when (val currentStateList = state.list) {
            SearchTrackState.Tracks.Empty -> ContentData(tracks = emptyList(), onClick = {})
            SearchTrackState.Tracks.Loading -> {}
            is SearchTrackState.Tracks.TracksData -> ContentData(
                tracks = currentStateList.listTracks,
                onClick = {})
        }
    }

}

@Composable
private fun ContentData(
    tracks: List<Track>,
    onClick: () -> Unit
) {

    Spacer(modifier = Modifier.padding(16.dp))
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        state = rememberLazyListState(),
    ) {
        if (tracks.isNotEmpty()) {
            items(tracks) { track ->
                AvPlayerTrackItem(
                    titleName = track.title,
                    authorName = track.artistName,
                    coverUrl = track.coverUrl
                )
            }
        }

    }
}
