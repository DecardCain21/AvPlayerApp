package com.example.player.ui.searchscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.player.domain.models.Track
import com.example.player.ui.composable.AvPlayerTextField
import com.example.player.ui.composable.AvPlayerTrackItem
import com.example.player.ui.searchscreen.state.SearchTrackState
import com.example.player.ui.searchscreen.state.SearchTrackUiEvent
import org.koin.androidx.compose.koinViewModel

@Composable
public fun SearchTracksScreen(
    viewModel: SearchTrackViewModel = koinViewModel(),
    navigateToPlayerScreen: (Long) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.padding(top = 16.dp))


        AvPlayerTextField(
            /*value = when (val currentStateInput = state.inputValue) {
                is SearchTrackState.Input.Empty -> ""
                is SearchTrackState.Input.Query -> currentStateInput.text
            }*/
            onValueChange = {
                viewModel.handleEventSearchTrackScreen(
                    SearchTrackUiEvent.InputQuery(
                        query = it
                    )
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = ""
                )
            }
        )

        when (val currentStateList = state.list) {
            SearchTrackState.Tracks.Empty -> ContentData(
                tracks = emptyList(),
                navigateToPlayerScreen = {},
            )

            SearchTrackState.Tracks.Loading -> {}
            is SearchTrackState.Tracks.TracksData -> ContentData(
                tracks = currentStateList.listTracks,
                navigateToPlayerScreen = navigateToPlayerScreen,
            )
        }
    }

}

@Composable
private fun ContentData(
    tracks: List<Track>,
    navigateToPlayerScreen: (Long) -> Unit = {}
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
                    coverUrl = track.coverUrl,
                    onClick = {
                        navigateToPlayerScreen(track.id)
                    }
                )
            }
        }

    }
}
