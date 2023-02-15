package com.iish.fojinapp.repository.cache.implementation

import com.iish.fojinapp.domain.entity.Review
import com.iish.fojinapp.repository.cache.abstraction.ReviewDaoService
import com.iish.fojinapp.repository.cache.mapper.CacheMapper
import com.iish.fojinapp.repository.database.FojinDao
import com.iish.fojinapp.repository.database.returnOrderedQuery
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewDaoServiceImpl
@Inject
constructor(
    private val fojinDao: FojinDao,
    private val reviewsMapper: CacheMapper
) : ReviewDaoService {

    override suspend fun insertReview(review: Review): Long {
        return fojinDao.insertReview(reviewsMapper.mapToEntity(review))
    }

    override suspend fun insertReviews(reviews: List<Review>): LongArray {
        return fojinDao.insertReviews(
            reviewsMapper.reviewListToEntityList(reviews)
        )
    }

    override suspend fun searchReviewById(id: String): Review? {
        return fojinDao.searchReviewById(id)?.let { review ->
            reviewsMapper.mapFromEntity(review)
        }
    }

    override suspend fun getAllReviews(): List<Review> {
        return reviewsMapper.entityListToReviewList(fojinDao.getAllReviews())
    }

    override suspend fun searchReviewByTitleDESC(query: String, page: Int, pageSize: Int): List<Review> {
        return reviewsMapper.entityListToReviewList(
            fojinDao.searchReviewByTitleDESC(
                query = query,
                page = page,
                pageSize = pageSize
            )
        )
    }

    override suspend fun returnOrderedQuery(
        query: String,
        page: Int
    ): List<Review> {
        return reviewsMapper.entityListToReviewList(
            fojinDao.returnOrderedQuery(
                query = query,
                page = page
            )
        )
    }
}