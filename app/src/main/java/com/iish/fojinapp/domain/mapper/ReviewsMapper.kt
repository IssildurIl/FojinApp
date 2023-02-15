package com.iish.fojinapp.domain.mapper

import androidx.paging.PagingSource
import com.iish.fojinapp.domain.entity.MovieReviews
import com.iish.fojinapp.domain.entity.Review

interface ReviewsMapper<in REVIEW, in MOVIE_REVIEWS> {

    fun mapResponse(reviewDto: REVIEW): Review

    fun mapResponse(reviewsDto: List<REVIEW>): List<Review>

    fun mapResponse(movieReviewsResponse: MOVIE_REVIEWS): MovieReviews

    fun mapResponse(
        page: Int,
        movieReviewsResponse: MOVIE_REVIEWS
    ): PagingSource.LoadResult<Int, Review>
}