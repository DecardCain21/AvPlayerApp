package com.example.core.domain.models

public data class Track(
    public val id: Long,
    public val readable: Boolean,
    public val title: String,
    public val titleShort: String,
    public val titleVersion: String,
    public val link: String,
    public val duration: Int,
    public val rank: Long,
    public val explicitLyrics: Boolean,
    public val explicitContentLyrics: Int,
    public val explicitContentCover: Int,
    public val preview: String,
    public val md5Image: String,
    public val artist: String,
    public val album: String,
    public val type: String
)