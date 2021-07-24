package com.github.nyafunta.rssreader.infra.dependencies

import com.github.nyafunta.rssreader.domain.infra.HatenaFeedRepository
import com.github.nyafunta.rssreader.infra.HatenaFeedRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class Binds {

    @Binds
    abstract fun bindHatenaFeedRepository(repository: HatenaFeedRepositoryImpl): HatenaFeedRepository

}