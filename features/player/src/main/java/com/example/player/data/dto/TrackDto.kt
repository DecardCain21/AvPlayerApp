package com.example.player.data.dto

import com.example.player.domain.models.Track
import com.google.gson.annotations.SerializedName


internal class TrackDto(
    @SerializedName("id") val id: Long,
    @SerializedName("readable") val readable: Boolean?,
    @SerializedName("title") val title: String?,
    @SerializedName("title_short") val titleShort: String?,
    @SerializedName("title_version") val titleVersion: String?,
    @SerializedName("link") val link: String?,
    @SerializedName("duration") val duration: Int?,
    @SerializedName("rank") val rank: Long?,
    @SerializedName("explicit_lyrics") val explicitLyrics: Boolean?,
    @SerializedName("explicit_content_lyrics") val explicitContentLyrics: Int?,
    @SerializedName("explicit_content_cover") val explicitContentCover: Int?,
    @SerializedName("preview") val preview: String?,
    @SerializedName("md5_image") val md5Image: String?,
    @SerializedName("artist") val artist: ArtistDto?,
    @SerializedName("album") val album: AlbumDto?,
    @SerializedName("type") val type: String?
)

internal class ArtistDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("link") val link: String,
    @SerializedName("picture") val picture: String,
    @SerializedName("picture_small") val pictureSmall: String,
    @SerializedName("picture_medium") val pictureMedium: String,
    @SerializedName("picture_big") val pictureBig: String,
    @SerializedName("picture_xl") val pictureXl: String,
    @SerializedName("tracklist") val trackList: String,
    @SerializedName("type") val type: String,
    @SerializedName("radio") val radio: Boolean = false
)

internal class AlbumDto(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("cover") val cover: String,
    @SerializedName("cover_small") val coverSmall: String,
    @SerializedName("cover_medium") val coverMedium: String,
    @SerializedName("cover_big") val coverBig: String,
    @SerializedName("cover_xl") val coverXl: String,
    @SerializedName("md5_image") val md5Image: String,
    @SerializedName("tracklist") val trackList: String,
    @SerializedName("type") val type: String
)

internal fun TrackDto.convertToTrack(): Track = Track(
    id = id,
    readable = readable ?: false,
    title = title.orEmpty(),
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
