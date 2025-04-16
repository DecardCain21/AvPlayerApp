package com.example.searchtracks.data.dto

import com.google.gson.annotations.SerializedName


public class TrackDto(
    @SerializedName("id")
    public val id: Long,
    @SerializedName("readable")
    public val readable: Boolean,
    @SerializedName("title")
    public val title: String,
    @SerializedName("title_short")
    public val title_short: String,
    @SerializedName("title_version")
    public val title_version: String,
    @SerializedName("link")
    public val link: String,
    @SerializedName("duration")
    public val duration: Int,
    @SerializedName("rank")
    public val rank: Long,
    @SerializedName("explicit_lyrics")
    public val explicit_lyrics: Boolean,
    @SerializedName("explicit_content_lyrics")
    public val explicit_content_lyrics: Int,
    @SerializedName("explicit_content_cover")
    public val explicit_content_cover: Int,
    @SerializedName("preview")
    public val preview: String,
    @SerializedName("md5_image")
    public val md5_image: String,
    @SerializedName("artist")
    public val artist: ArtistDto,
    @SerializedName("album")
    public val album: AlbumDto,
    @SerializedName("type")
    public val type: String
)

public class ArtistDto(
    @SerializedName("id")
    public val id: Long,
    @SerializedName("name")
    public val name: String,
    @SerializedName("link")
    public val link: String,
    @SerializedName("picture")
    public val picture: String,
    @SerializedName("picture_small")
    public val picture_small: String,
    @SerializedName("picture_medium")
    public val picture_medium: String,
    @SerializedName("picture_big")
    public val picture_big: String,
    @SerializedName("picture_xl")
    public val picture_xl: String,
    @SerializedName("tracklist")
    public val tracklist: String,
    @SerializedName("type")
    public val type: String
)

public class AlbumDto(
    @SerializedName("id")
    public val id: Long,
    @SerializedName("title")
    public val title: String,
    @SerializedName("cover")
    public val cover: String,
    @SerializedName("cover_small")
    public val cover_small: String,
    @SerializedName("cover_medium")
    public val cover_medium: String,
    @SerializedName("cover_big")
    public val cover_big: String,
    @SerializedName("cover_xl")
    public val cover_xl: String,
    @SerializedName("md5_image")
    public val md5_image: String,
    @SerializedName("tracklist")
    public val tracklist: String,
    @SerializedName("type")
    public val type: String
)
