package com.github.nyafunta.rssreader.ui.feed

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.transform.BlurTransformation
import coil.transform.RoundedCornersTransformation
import com.github.nyafunta.rssreader.domain.model.RssFeed
import com.xwray.groupie.GroupieAdapter
import kotlinx.coroutines.Dispatchers

@BindingAdapter("rss_feed")
fun RecyclerView.setRssFeed(rssFeed: RssFeed?) {
    rssFeed ?: return

    if (adapter == null) {
        adapter = GroupieAdapter()
    }

    rssFeed.items
        .filterNot {
            it.title.isNullOrEmpty() || it.description.isNullOrEmpty()
        }
        .map {
            android.util.Log.e("DEBUG", "item ${it.image}")
            RssFeedItemDataStore(requireNotNull(it.title), requireNotNull(it.description), it.image.orEmpty())
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

            val result = context.imageLoader.execute(request)

            if (result is SuccessResult) {
                val key = result.metadata.memoryCacheKey
                if (key != null) {
                    android.util.Log.e("DEBUG", "from cache")
                    context.imageLoader.memoryCache[key]
                } else {
                    android.util.Log.e("DEBUG", "not found key")
                    result.drawable
                }

                setImageDrawable(result.drawable)
            }
        }
    }

}