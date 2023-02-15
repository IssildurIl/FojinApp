package com.iish.fojinapp.api.mapper

import androidx.paging.PagingSource
import com.iish.fojinapp.api.dto.ReviewDto
import com.iish.fojinapp.api.dto.ReviewsResponse
import com.iish.fojinapp.domain.entity.Link
import com.iish.fojinapp.domain.entity.MovieReviews
import com.iish.fojinapp.domain.entity.Multimedia
import com.iish.fojinapp.domain.entity.Review
import com.iish.fojinapp.domain.mapper.ReviewsMapper
import com.iish.fojinapp.utils.paginator.ReviewsPageSource.Companion.PAGE_STEP
import javax.inject.Inject

class ReviewsMapperImpl @Inject constructor(
) :
    ReviewsMapper<ReviewDto, ReviewsResponse> {

    override fun mapResponse(
        page: Int,
        movieReviewsResponse: ReviewsResponse
    ): PagingSource.LoadResult<Int, Review> {
        return PagingSource.LoadResult.Page(
            data = mapResponse(movieReviewsResponse.reviewResponse ?: emptyList()),
            prevKey = if (page == 0) null else page.minus(PAGE_STEP),
            nextKey = if (movieReviewsResponse.hasMore == false) null else page.plus(PAGE_STEP)
        )
    }

    override fun mapResponse(reviewDto: ReviewDto): Review {
        return with(reviewDto) {
            Review(
                title = displayTitle ?: "no title",
                descriptionShort = summaryShort ?: "no description",
                byline = byline,
                criticsPick = criticsPick,
                dateUpdated = dateUpdated,
                headline = headline,
                link = Link(type = link?.type, urlPage = link?.urlPage, suggestedLinkText = link?.suggestedLinkText),
                rating = rating,
                multimedia = Multimedia(type = multimedia?.type, srcUrl = multimedia?.srcUrl, heightSrc = multimedia?.heightSrc, width = multimedia?.width),
                openingDate = openingDate,
                publicationDate = publicationDate,
                summaryShort = summaryShort
            )
        }
    }

    override fun mapResponse(reviewsDto: List<ReviewDto>): List<Review> {
        return reviewsDto.map {
            mapResponse(it)
        }
    }

    override fun mapResponse(movieReviewsResponse: ReviewsResponse): MovieReviews {
        return with(movieReviewsResponse) {
            MovieReviews(
                hasMore = hasMore,
                numResults = numResults,
                reviews = mapResponse(reviewResponse ?: emptyList())
            )
        }
    }
}