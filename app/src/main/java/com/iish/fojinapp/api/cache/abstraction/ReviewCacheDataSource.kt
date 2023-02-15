package com.iish.fojinapp.api.cache.abstraction

import com.iish.fojinapp.domain.entity.Review

interface ReviewCacheDataSource {

    suspend fun insertReview(review: Review): Long

    suspend fun insertReviews(reviews: List<Review>): LongArray

    suspend fun getAllReviews(): List<Review>

    suspend fun searchReviewById(id: String): Review?

    suspend fun searchReview(
        query: String,
        page: Int
    ):List<Review>

}