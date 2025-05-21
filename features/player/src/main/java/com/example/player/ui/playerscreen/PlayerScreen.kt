package com.example.player.ui.playerscreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.player.domain.models.Track
import com.example.player.ui.AudioPlayerWithControls
import com.example.player.ui.playerscreen.state.PlayerScreenState
import org.koin.androidx.compose.koinViewModel

@Composable
public fun PlayerScreen(
    modifier: Modifier = Modifier,
    trackId: Long = 0,
    viewModel: PlayerScreenViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val isServiceRunning by rememberSaveable { mutableStateOf(false) }
    viewModel.loadTrackById(trackId = trackId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 16.dp)
    ) {
        when (val currentStateTrack = state.trackState) {
            PlayerScreenState.TrackState.EmptyTrack -> null//TODO()
            is PlayerScreenState.TrackState.TrackData -> {
                with(currentStateTrack) {
                    viewModel.addMediaItemService(
                        url = track.preview,
                        albumArtist = track.albumTitle,
                        displayTitle = track.title,
                        subTitle = track.artistName
                    )
                    TrackContent(track)
                }
            }
        }
    }

}

@Composable
private fun TrackContent(track: Track) {
    AsyncImage(
        model = track.coverUrl,
        contentDescription = "Track cover",
        modifier = Modifier.wrapContentSize(),
        contentScale = ContentScale.Crop
    )
    Spacer(modifier = Modifier.padding(16.dp))
    Text(text = track.title, color = Color.White)
    Spacer(modifier = Modifier.padding(16.dp))
    Text(text = track.artistName, color = Color.White)
    AudioPlayerWithControls(url = track.preview)
}