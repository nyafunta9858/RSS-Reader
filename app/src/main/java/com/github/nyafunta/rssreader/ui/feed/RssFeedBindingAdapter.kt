package com.github.nyafunta.rssreader.ui.feed

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.github.nyafunta.rssreader.domain.model.RssFeed
import com.xwray.groupie.GroupieAdapter
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

private const val DATE_PARSE_FORMAT_PATTERN = "yyyy-MM-dd\'T\'HH:mm:ss\'Z\'"
private const val DATE_DISPLAY_FORMAT_PATTERN = "yyyy/MM/dd HH:mm"
private const val DATE_PARSE_ERROR = "----/--/-- --:--"


@BindingAdapter("rss_feed")
fun RecyclerView.setRssFeed(rssFeed: RssFeed?) {
    rssFeed ?: return

    if (adapter == null) {
        adapter = GroupieAdapter()
    }

    rssFeed.items
        .filterNot {
            it.title.isNullOrEmpty() || it.description.isNullOrEmpty() || it.date.isNullOrEmpty()
        }
        .map {
            RssFeedItemDataStore(requireNotNull(it.title), requireNotNull(it.description), it.image.orEmpty(), requireNotNull(it.date))
        }
        .map {
            RssFeedItem(it)
        }
        .let {
            (adapter as GroupieAdapter).addAll(it)
        }
}

@BindingAdapter("image_url")
fun ImageView.loadImage(imageUrl: String?) {
    val url = imageUrl.takeUnless { it.isNullOrEmpty() } ?: return

    findViewTreeLifecycleOwner()?.let { owner ->
        owner.lifecycleScope.launchWhenResumed {
            val request = ImageRequest.Builder(context)
                .data(url)
                .lifecycle(owner)
                .build()

            visibility = View.INVISIBLE

            val result = context.imageLoader.execute(request)

            if (result is SuccessResult) {
                val key = result.metadata.memoryCacheKey
                if (key != null) {
                    context.imageLoader.memoryCache[key]
                } else {
                    result.drawable
                }

                setImageDrawable(result.drawable)

                visibility = View.VISIBLE
            }
        }
    }
}

@BindingAdapter("publish_date")
fun TextView.setPublishDate(date: String?) {
    date.takeUnless { it.isNullOrEmpty() } ?: return

    kotlin.runCatching {
        LocalDateTime
            .parse(date, DateTimeFormatter.ofPattern(DATE_PARSE_FORMAT_PATTERN))
            .format(DateTimeFormatter.ofPattern(DATE_DISPLAY_FORMAT_PATTERN))
    }.onSuccess {
        text = it
    }.onFailure {
        text = DATE_PARSE_ERROR
    }
}