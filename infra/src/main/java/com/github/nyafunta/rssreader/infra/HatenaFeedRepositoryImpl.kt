package com.github.nyafunta.rssreader.infra

import com.github.nyafunta.rssreader.domain.infra.HatenaFeedRepository
import com.github.nyafunta.rssreader.domain.infra.enum.Category
import com.github.nyafunta.rssreader.infra.dependencies.network.HatenaApi
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class HatenaFeedRepositoryImpl @Inject internal constructor(
    private val api: HatenaApi
) : HatenaFeedRepository {

    override suspend fun all() {
        val response = api.all()
        response.toLog()
    }

    override suspend fun get(category: Category) {
        val response = api.get(category.raw)
        response.toLog()
    }

    // for debug
    private fun Response<ResponseBody>.toLog() {
        body()
            ?.charStream()
            ?.runCatching {
                use {
                    it.forEachLine { line ->
                        android.util.Log.e("DEBUG", line)
                    }
                }
            }
    }

}