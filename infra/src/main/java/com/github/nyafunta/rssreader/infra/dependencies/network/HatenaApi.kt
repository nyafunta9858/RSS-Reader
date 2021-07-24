package com.github.nyafunta.rssreader.infra.dependencies.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface HatenaApi {

    @GET("hotentry.rss")
    suspend fun all(): Response<ResponseBody>

    @GET("hotentry/{category}.rss")
    suspend fun get(@Path("category") category: String): Response<ResponseBody>

}