package com.iish.fojinapp.ui.utils.implementation

import com.iish.fojinapp.api.cache.abstraction.ReviewCacheDataSource
import com.iish.fojinapp.domain.entity.Review
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertReviewsImpl @Inject constructor(
    private val reviewsCacheDataSource: ReviewCacheDataSource
) {

    suspend fun insertReviews(
        reviewsList: List<Review>
    ) {
        withContext(Dispatchers.IO) {
            reviewsCacheDataSource.insertReviews(
                reviewsList
            )
        }
    }
}