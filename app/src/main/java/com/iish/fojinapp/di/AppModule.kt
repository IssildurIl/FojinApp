package com.iish.fojinapp.di

import com.iish.fojinapp.api.cache.abstraction.ReviewCacheDataSource
import com.iish.fojinapp.api.cache.implementation.ReviewCacheDataSourceImpl
import com.iish.fojinapp.api.dto.LinkDto
import com.iish.fojinapp.api.dto.MultimediaDto
import com.iish.fojinapp.api.dto.ReviewDto
import com.iish.fojinapp.api.dto.ReviewsResponse
import com.iish.fojinapp.api.mapper.ReviewsMapperImpl
import com.iish.fojinapp.api.repository.ReviewsRepositoryImpl
import com.iish.fojinapp.domain.entity.Link
import com.iish.fojinapp.domain.entity.Multimedia
import com.iish.fojinapp.domain.entity.Review
import com.iish.fojinapp.domain.mapper.EntityMapper
import com.iish.fojinapp.domain.mapper.ReviewsMapper
import com.iish.fojinapp.domain.repository.ReviewsRepository
import com.iish.fojinapp.repository.cache.mapper.CacheMapper
import com.iish.fojinapp.repository.cache.model.ReviewCacheEntity
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Reusable
    @Binds
    fun bindsReviewsMapper(
        reviewsMapper: ReviewsMapperImpl
    ): @JvmSuppressWildcards ReviewsMapper<ReviewDto, ReviewsResponse>

    @Reusable
    @Binds
    fun bindsCacheMapper(
        cacheMapper: CacheMapper
    ): @JvmSuppressWildcards EntityMapper<ReviewCacheEntity, Review>

    @Reusable
    @Binds
    fun bindsReviewsRepository(reviewsRepository: ReviewsRepositoryImpl): ReviewsRepository

    @Reusable
    @Binds
    fun bindsReviewCacheDataSource(
        reviewCacheDataSourceImpl: ReviewCacheDataSourceImpl
    ): ReviewCacheDataSource

}