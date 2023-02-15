package com.iish.fojinapp.api.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.iish.fojinapp.domain.entity.Review
import com.iish.fojinapp.domain.repository.ReviewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewsRepositoryImpl @Inject constructor(
    private var reviewsSource: PagingSource<Int, Review>
) : ReviewsRepository {

    private val configReviewSource = PagingConfig(
        pageSize = 10, enablePlaceholders = false, initialLoadSize = 3, maxSize = 400
    )

    override fun getReviews(): Flow<PagingData<Review>> {
        return Pager(
            config = configReviewSource,
            pagingSourceFactory = { reviewsSource },
            initialKey = 1
        ).flow
    }

}