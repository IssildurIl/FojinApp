package com.iish.fojinapp.api.cache.implementation

import com.iish.fojinapp.api.cache.abstraction.ReviewCacheDataSource
import com.iish.fojinapp.domain.entity.Review
import com.iish.fojinapp.repository.cache.abstraction.ReviewDaoService
import javax.inject.Inject

class ReviewCacheDataSourceImpl
@Inject constructor(
    private val reviewDaoService: ReviewDaoService
) : ReviewCacheDataSource {

    override suspend fun insertReview(review: Review): Long {
        return reviewDaoService.insertReview(review)
    }

    override suspend fun insertReviews(reviews: List<Review>): LongArray {
        return reviewDaoService.insertReviews(reviews)
    }

    override suspend fun getAllReviews(): List<Review> {
        return reviewDaoService.getAllReviews()
    }

    override suspend fun searchReviewById(id: String): Review? {
        return reviewDaoService.searchReviewById(id)
    }

    override suspend fun searchReview(query: String, page: Int): List<Review> {
        return reviewDaoService.returnOrderedQuery(
            query, page
        )
    }
}