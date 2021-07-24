package com.github.nyafunta.rssreader.infra.dependencies

import com.github.nyafunta.rssreader.infra.dependencies.network.HatenaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Provides {

    @Singleton
    @Provides
    internal fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .build()

        return Retrofit.Builder()
            .baseUrl("https://b.hatena.ne.jp/")
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    internal fun provideHatenaApi(retrofit: Retrofit): HatenaApi {
        return retrofit.create(HatenaApi::class.java)
    }

}