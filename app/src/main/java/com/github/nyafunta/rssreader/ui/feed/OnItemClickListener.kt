package com.github.nyafunta.rssreader.ui.feed

fun interface OnItemClickListener {
    operator fun invoke(position: Int)
}