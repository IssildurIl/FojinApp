package com.iish.fojinapp.ui.utils.implementation

import com.iish.fojinapp.api.cache.abstraction.ReviewCacheDataSource
import com.iish.fojinapp.domain.entity.Review
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchReviewsImpl @Inject constructor(
    private var reviewsCacheDataSource: ReviewCacheDataSource
) {

    fun searchReviews(
        query: String,
        page: Int
    ): Flow<List<Review>> = flow {
        val cacheResult = withContext(Dispatchers.IO) {
            reviewsCacheDataSource.searchReview(
                query = query,
                page = page
            )
        }
        emit(cacheResult)
    }
}