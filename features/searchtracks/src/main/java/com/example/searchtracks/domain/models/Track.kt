package com.example.searchtracks.domain.models

public data class Track(
    public val id: Long,
    public val readable: Boolean,
    public val title: String,
    public val title_short: String,
    public val title_version: String,
    public val link: String,
    public val duration: Int,
    public val rank: Long,
    public val explicit_lyrics: Boolean,
    public val explicit_content_lyrics: Int,
    public val explicit_content_cover: Int,
    public val preview: String,
    public val md5_image: String,
    public val artist: String,
    public val album: String,
    public val type: String
)