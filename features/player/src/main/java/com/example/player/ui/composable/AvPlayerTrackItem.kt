package com.example.player.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
public fun AvPlayerTrackItem(
    titleName: String,
    authorName: String,
    coverUrl: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}

) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.Green,
        modifier = Modifier
            .wrapContentSize()

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Gray)
        ) {
            AsyncImage(
                model = coverUrl,
                contentDescription = "Track cover",
                modifier = Modifier.wrapContentSize(),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(text = titleName, color = Color.White)
                Spacer(modifier = Modifier.padding(16.dp))
                Text(text = authorName, color = Color.White)
            }

        }
    }

}