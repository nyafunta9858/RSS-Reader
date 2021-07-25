package com.github.nyafunta.rssreader.infra

import com.github.nyafunta.rssreader.domain.infra.HatenaFeedRepository
import com.github.nyafunta.rssreader.domain.infra.predefine.Category
import com.github.nyafunta.rssreader.domain.model.RssFeed
import com.github.nyafunta.rssreader.infra.dependencies.network.HatenaApi
import javax.inject.Inject

class HatenaFeedRepositoryImpl @Inject internal constructor(
    private val api: HatenaApi
) : HatenaFeedRepository {

    override suspend fun fetchAll(): RssFeed = api.all()

    override suspend fun fetchByCategory(category: Category): RssFeed = api.get(category.raw)

}