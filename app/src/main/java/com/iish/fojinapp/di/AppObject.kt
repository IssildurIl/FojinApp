package com.iish.fojinapp.di

import com.iish.fojinapp.App
import com.iish.fojinapp.api.cache.abstraction.ReviewCacheDataSource
import com.iish.fojinapp.ui.utils.implementation.SearchReviewsImpl
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppObject {

    @Singleton
    @Provides
    fun bindApp(): App {
        return App()
    }

    @Singleton
    @Provides
    fun provideSearchReviewsImpl(reviewsCacheDataSource: ReviewCacheDataSource): SearchReviewsImpl {
        return SearchReviewsImpl(
            reviewsCacheDataSource
        )
    }
}