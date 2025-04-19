package com.example.core.extantions

import com.example.core.domain.models.Track
import com.example.core.network.dto.TrackDto

public fun TrackDto.convertToTrack(): Track = Track(
    id = id,
    readable = readable ?: false,
    title = title ?: "",
    titleShort = titleShort ?: "",
    titleVersion = titleVersion ?: "",
    link = link ?: "",
    duration = duration ?: 0,
    rank = rank ?: 0,
    explicitLyrics = explicitLyrics ?: false,
    explicitContentLyrics = explicitContentLyrics ?: 0,
    explicitContentCover = explicitContentCover ?: 0,
    preview = preview ?: "",
    md5Image = md5Image ?: "",
    artistName = artist?.name ?: "",
    albumTitle = album?.title ?: "",
    type = type ?: ""
)