package com.github.nyafunta.rssreader.domain.infra

import com.github.nyafunta.rssreader.domain.infra.predefine.Category
import com.github.nyafunta.rssreader.domain.model.RssFeed

interface HatenaFeedRepository {

    suspend fun fetchAll(): RssFeed

    suspend fun fetchByCategory(category: Category): RssFeed

}