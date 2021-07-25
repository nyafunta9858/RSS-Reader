package com.github.nyafunta.rssreader.infra

import com.github.nyafunta.rssreader.domain.infra.HatenaFeedRepository
import com.github.nyafunta.rssreader.domain.infra.enum.Category
import com.github.nyafunta.rssreader.domain.model.RssFeed
import com.github.nyafunta.rssreader.infra.dependencies.network.HatenaApi
import javax.inject.Inject

class HatenaFeedRepositoryImpl @Inject internal constructor(
    private val api: HatenaApi
) : HatenaFeedRepository {

    override suspend fun getAll(): RssFeed = api.all().also(::toLog)

    override suspend fun get(category: Category): RssFeed = api.get(category.raw).also(::toLog)

    // for debug
    private fun toLog(feed: RssFeed) {
        android.util.Log.e("DEBUG", "size : ${feed.items.size}")
        feed.items.forEachIndexed { index, rssItem ->
            android.util.Log.e("DEBUG", "No.$index $rssItem")
        }
    }

}