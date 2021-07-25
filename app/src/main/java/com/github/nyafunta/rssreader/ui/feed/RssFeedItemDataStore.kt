package com.github.nyafunta.rssreader.ui.feed

data class RssFeedItemDataStore(
    val title: String,
    val description: String,
    val imageUrl: String
) {
    val isThumbnailVisible = imageUrl.isNotEmpty()
}