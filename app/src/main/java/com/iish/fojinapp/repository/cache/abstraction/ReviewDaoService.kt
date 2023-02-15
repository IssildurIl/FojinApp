package com.iish.fojinapp.repository.cache.abstraction

import com.iish.fojinapp.domain.entity.Review
import com.iish.fojinapp.repository.database.REVIEW_PAGINATION_PAGE_SIZE

interface ReviewDaoService {

    suspend fun insertReview(review: Review): Long

    suspend fun insertReviews(reviews: List<Review>): LongArray

    suspend fun searchReviewById(id: String): Review?

    suspend fun getAllReviews(): List<Review>

    suspend fun searchReviewByTitleDESC(
        query: String,
        page: Int,
        pageSize: Int = REVIEW_PAGINATION_PAGE_SIZE
    ): List<Review>

    suspend fun returnOrderedQuery(
        query: String,
        page: Int
    ): List<Review>
}