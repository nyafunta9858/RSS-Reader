package com.github.nyafunta.rssreader.ui.feed

fun interface OnRssItemClickListener {
    operator fun invoke(link: String)
}