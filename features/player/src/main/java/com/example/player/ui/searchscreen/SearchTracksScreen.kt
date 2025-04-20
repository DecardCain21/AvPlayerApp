package com.example.player.ui.searchscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.player.domain.models.Track
import com.example.player.ui.composable.AvPlayerTrackItem

@Composable
public fun SearchTracksScreen(
    modifier: Modifier = Modifier,
    testList: List<Track> = emptyList()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.TopCenter),
            text = "SearchTracksScreen"
        )
        Spacer(modifier = Modifier.padding(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = rememberLazyListState(),
        ) {
            items(testList) { track ->
                AvPlayerTrackItem(
                    titleName = track.title,
                    authorName = track.artistName,
                    coverUrl = track.coverUrl
                )
            }
        }
    }
}