package com.iish.fojinapp.domain.repository

import androidx.paging.PagingData
import com.iish.fojinapp.domain.entity.Review
import kotlinx.coroutines.flow.Flow

interface ReviewsRepository {

    fun getReviews(): Flow<PagingData<Review>>
}