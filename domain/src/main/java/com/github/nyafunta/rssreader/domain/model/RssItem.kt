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
    var publishDate: String? = null
    var description: String? = null


    override fun toString(): String {
        val builder = StringBuilder()
        if (title != null) {
            builder.append(title).append("\n")
        }
        if (link != null) {
            builder.append(link).append("\n")
        }
        if (image != null) {
            builder.append(image).append("\n")
        }
        if (description != null) {
            builder.append(description)
        }
        return builder.toString()
    }
}