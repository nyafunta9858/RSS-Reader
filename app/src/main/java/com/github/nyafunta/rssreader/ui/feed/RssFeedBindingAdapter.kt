package com.github.nyafunta.rssreader.ui.feed

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.github.nyafunta.rssreader.R
import com.github.nyafunta.rssreader.domain.infra.predefine.Category
import com.github.nyafunta.rssreader.domain.model.RssFeed
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.xwray.groupie.GroupieAdapter
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

private const val DATE_PARSE_FORMAT_PATTERN = "yyyy-MM-dd\'T\'HH:mm:ss\'Z\'"
private const val DATE_DISPLAY_FORMAT_PATTERN = "yyyy/MM/dd HH:mm"
private const val DATE_PARSE_ERROR = "----/--/-- --:--"


@BindingAdapter("rss_feed", "listener")
fun RecyclerView.setRssFeed(rssFeed: RssFeed?, listener: OnRssItemClickListener) {
    rssFeed ?: return

    if (adapter == null) {
        adapter = GroupieAdapter()
    }

    rssFeed.items
        .filterNot {
            it.title.isNullOrEmpty() || it.description.isNullOrEmpty() || it.date.isNullOrEmpty()
        }
        .map {
            RssFeedItemDataStore(
                requireNotNull(it.title),
                requireNotNull(it.description),
                it.image.orEmpty(),
                requireNotNull(it.link),
                requireNotNull(it.date)
            )
        }
        .mapIndexed { index, store ->
            if (index == 0) {
                RssFeedItem(store, listener)
            } else {
                RssFeedItemSecondary(store, listener)
            }
        }
        .let {
            (adapter as GroupieAdapter).addAll(it)
        }
}

@BindingAdapter("image_url")
fun ImageView.loadImage(imageUrl: String?) {
    val url = imageUrl.takeUnless { it.isNullOrEmpty() } ?: run {
        visibility = View.GONE
        return
    }

    findViewTreeLifecycleOwner()?.let { owner ->
        owner.lifecycleScope.launchWhenResumed {
            val request = ImageRequest.Builder(context)
                .data(url)
                .lifecycle(owner)
                .build()

            visibility = View.GONE

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

@BindingAdapter("tab_layout", "categories")
fun ViewPager2.setTabLayout(tabLayout: TabLayout, categories: List<Category>) {
    (adapter as? RssFeedCategoryPagerAdapter)?.update(categories) ?: return

    TabLayoutMediator(tabLayout, this) { tab, position ->
        val category = categories[position]
        tab.setText(category.toStringRes())
    }.attach()
}

@StringRes
private fun Category.toStringRes() = when (this) {
    Category.ALL -> R.string.category_all
    Category.GENERAL -> R.string.category_general
    Category.SOCIAL -> R.string.category_social
    Category.ECONOMICS -> R.string.category_economics
    Category.LIFE -> R.string.category_life
    Category.KNOWLEDGE -> R.string.category_knowledge
    Category.IT -> R.string.category_it
    Category.FUN -> R.string.category_fun
    Category.ENTERTAINMENT -> R.string.category_entertainment
    Category.GAME -> R.string.category_game
}