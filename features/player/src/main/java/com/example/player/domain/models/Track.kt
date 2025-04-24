package com.example.player.domain.models

public data class Track(
    public val id: Long,
    public val readable: Boolean,
    public val title: String,
    public val coverUrl: String,
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
    public val artistName: String,
    public val albumTitle: String,
    public val type: String
) {
    public companion object {
        public val emptyTrack: Track = Track(
            id = -1,
            readable = false,
            title = "",
            coverUrl = "",
            titleShort = "",
            titleVersion = "",
            link = "",
            duration = 0,
            rank = 0,
            explicitLyrics = false,
            explicitContentLyrics = 0,
            explicitContentCover = 0,
            preview = "",
            md5Image = "",
            artistName = "",
            albumTitle = "",
            type = ""
        )
    }
}