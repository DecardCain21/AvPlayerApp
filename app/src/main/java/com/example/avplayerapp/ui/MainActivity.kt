package com.example.avplayerapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.avplayerapp.ui.theme.AvPlayerAppTheme
import com.example.core.domain.models.Track
import com.example.playback.domain.api.GetTrackByIdUseCase
import com.example.playback.ui.AudioPlayerWithControls
import com.example.searchtracks.domain.api.GetDeezerChartUseCase
import com.example.searchtracks.domain.api.GetDeezerTracksUseCase
import org.koin.android.ext.android.inject

public class MainActivity : ComponentActivity() {

    private val getDeezerTracksUseCase: GetDeezerTracksUseCase by inject()
    private val getDeezerChartUseCase: GetDeezerChartUseCase by inject()
    private val getTrackByIdUseCase: GetTrackByIdUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AvPlayerAppTheme {
                val tracksState = remember { mutableStateOf<Result<List<Track>>?>(null) }
                LaunchedEffect(Unit) {
                    tracksState.value = getDeezerTracksUseCase("Mick Gordon")
                    tracksState.value = getDeezerChartUseCase()
                    //tracksState.value = Result.success(listOf(getTrackByIdUseCase(2461123655).getOrThrow()))


                }
                Box(Modifier.fillMaxSize()) {
                    Scaffold(modifier = Modifier.padding(50.dp)) { innerPadding ->
                        tracksState.value?.getOrNull()
                            ?.let { TrackList(it, modifier = Modifier.padding(innerPadding)) }
                        tracksState.value?.getOrNull()?.first()
                            ?.let {
                                AudioPlayerWithControls(
                                    modifier = Modifier.padding(50.dp),
                                    url = it.preview
                                )
                            }
                    }
                }
            }
        }
    }
}


@Composable
private fun TrackList(tracks: List<Track>, modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        items(tracks) { track ->
            Text(text = track.title)
            // Другая информация о треке
        }
    }
}

@Composable
private fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    AvPlayerAppTheme {
        Greeting("Android")
    }
}