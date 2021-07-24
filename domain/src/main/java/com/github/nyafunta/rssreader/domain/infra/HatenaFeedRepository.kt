package com.github.nyafunta.rssreader.domain.infra

import com.github.nyafunta.rssreader.domain.infra.enum.Category

interface HatenaFeedRepository {

    suspend fun all()

    suspend fun get(category: Category)

}