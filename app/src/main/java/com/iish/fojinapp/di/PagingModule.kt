package com.iish.fojinapp.di

import androidx.paging.PagingSource
import com.iish.fojinapp.domain.entity.Review
import com.iish.fojinapp.utils.paginator.ReviewsPageSource
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PagingModule {

    @Reusable
    @Binds
    fun bindsReviewsPagingSource(
        reviewsPagingSource: ReviewsPageSource
    ): @JvmSuppressWildcards PagingSource<Int, Review>
}