package com.github.nyafunta.rssreader

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.CoilUtils
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient

@HiltAndroidApp
class RssReaderApplication : Application(), ImageLoaderFactory {

    override fun newImageLoader() = ImageLoader.Builder(applicationContext)
        .availableMemoryPercentage(0.25)
        .crossfade(true)
        .bitmapPoolPercentage(0.1)
        .okHttpClient {
            OkHttpClient.Builder()
                .cache(CoilUtils.createDefaultCache(applicationContext))
                .build()
        }
        .build()

}