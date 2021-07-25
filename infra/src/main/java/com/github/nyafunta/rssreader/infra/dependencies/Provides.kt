package com.github.nyafunta.rssreader.infra.dependencies

import com.github.nyafunta.rssreader.infra.convert.RssConverterFactory
import com.github.nyafunta.rssreader.infra.dependencies.network.HatenaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object Provides {

    @Provides
    internal fun provideRssConverterFactory(): Converter.Factory {
        return RssConverterFactory.create()
    }

    @Provides
    internal fun provideRetrofit(converterFactory: Converter.Factory): Retrofit {
        val client = OkHttpClient.Builder()
            .build()

        return Retrofit.Builder()
            .baseUrl("https://b.hatena.ne.jp/")
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    internal fun provideHatenaApi(retrofit: Retrofit): HatenaApi {
        return retrofit.create(HatenaApi::class.java)
    }

}