package com.iish.fojinapp.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iish.fojinapp.repository.cache.model.ReviewCacheEntity

const val REVIEW_PAGINATION_PAGE_SIZE = 20

@Dao
interface FojinDao {

    @Insert
    suspend fun insertReview(review: ReviewCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviews(reviews: List<ReviewCacheEntity>): LongArray

    @Query("SELECT * FROM review WHERE id = :id")
    suspend fun searchReviewById(id: String): ReviewCacheEntity?

    @Query(
        """
        SELECT * FROM review 
        WHERE display_title LIKE '%' || :query || '%' 
        OR summary_short LIKE '%' || :query || '%' 
        ORDER BY publication_date DESC LIMIT (:page * :pageSize)
        """
    )
    suspend fun searchReviewByTitleDESC(
        query: String,
        page: Int,
        pageSize: Int = REVIEW_PAGINATION_PAGE_SIZE
    ): List<ReviewCacheEntity>

    @Query("SELECT * FROM review")
    suspend fun getAllReviews(): List<ReviewCacheEntity>
}

suspend fun FojinDao.returnOrderedQuery(
    query: String,
    page: Int
): List<ReviewCacheEntity> {
    return searchReviewByTitleDESC(
        query = query,
        page = page
    )
}