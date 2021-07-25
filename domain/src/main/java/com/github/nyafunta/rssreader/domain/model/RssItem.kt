package com.github.nyafunta.rssreader.domain.model

import java.io.Serializable

class RssItem : Serializable {

    var title: String? = null
        set(title) {
            field = title?.replace("&#39;", "'")?.replace("&#039;", "'")
        }
    var link: String? = null
        set(link) {
            field = link?.trim { it <= ' ' }
        }
    var image: String? = null
    var date: String? = null
    var description: String? = null

    override fun toString(): String = buildString {
        if (title != null) {
            append(title).append("\n")
        }
        if (link != null) {
            append(link).append("\n")
        }
        if (image != null) {
            append(image).append("\n")
        }
        if (date != null) {
            append(date).append("\n")
        }
        if (description != null) {
            append(description)
        }
    }
}