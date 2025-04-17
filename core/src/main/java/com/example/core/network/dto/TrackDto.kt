package com.example.core.network.dto

import com.google.gson.annotations.SerializedName


public class TrackDto(
    public val id: Long,
    public val readable: Boolean?,
    public val title: String?,
    @SerializedName("title_short")
    public val titleShort: String?,
    @SerializedName("title_version")
    public val titleVersion: String?,
    public val link: String?,
    public val duration: Int?,
    public val rank: Long?,
    @SerializedName("explicit_lyrics")
    public val explicitLyrics: Boolean?,
    @SerializedName("explicit_content_lyrics")
    public val explicitContentLyrics: Int?,
    @SerializedName("explicit_content_cover")
    public val explicitContentCover: Int?,
    public val preview: String?,
    @SerializedName("md5_image")
    public val md5Image: String?,
    public val artist: ArtistDto?,
    public val album: AlbumDto?,
    public val type: String?
)

public class ArtistDto(
    public val id: Long,
    public val name: String,
    public val link: String,
    public val picture: String,
    @SerializedName("picture_small")
    public val pictureSmall: String,
    @SerializedName("picture_medium")
    public val pictureMedium: String,
    @SerializedName("picture_big")
    public val pictureBig: String,
    @SerializedName("picture_xl")
    public val pictureXl: String,
    @SerializedName("tracklist")
    public val trackList: String,
    public val type: String,
    public val radio: Boolean = false
)

public class AlbumDto(
    public val id: Long,
    public val title: String,
    public val cover: String,
    @SerializedName("cover_small")
    public val coverSmall: String,
    @SerializedName("cover_medium")
    public val coverMedium: String,
    @SerializedName("cover_big")
    public val coverBig: String,
    @SerializedName("cover_xl")
    public val coverXl: String,
    @SerializedName("md5_image")
    public val md5Image: String,
    @SerializedName("tracklist")
    public val trackList: String,
    @SerializedName("type")
    public val type: String
)
