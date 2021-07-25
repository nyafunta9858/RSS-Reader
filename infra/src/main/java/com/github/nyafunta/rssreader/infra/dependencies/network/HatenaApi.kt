package com.github.nyafunta.rssreader.infra.dependencies.network

import com.github.nyafunta.rssreader.domain.model.RssFeed
import retrofit2.http.GET
import retrofit2.http.Path

internal interface HatenaApi {

    @GET("hotentry.rss")
    suspend fun all(): RssFeed

    @GET("hotentry/{category}.rss")
    suspend fun get(@Path("category") category: String): RssFeed

}