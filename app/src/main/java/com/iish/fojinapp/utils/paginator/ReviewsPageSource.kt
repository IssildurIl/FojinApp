package com.iish.fojinapp.utils.paginator

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.iish.fojinapp.api.dto.ReviewDto
import com.iish.fojinapp.api.dto.ReviewsResponse
import com.iish.fojinapp.api.source.ReviewsApi
import com.iish.fojinapp.domain.entity.Review
import com.iish.fojinapp.domain.mapper.ReviewsMapper
import retrofit2.HttpException
import javax.inject.Inject

class ReviewsPageSource @Inject constructor(
    private val api: ReviewsApi,
    private val mapper: @JvmSuppressWildcards ReviewsMapper<ReviewDto, ReviewsResponse>
) : PagingSource<Int, Review>() {

    companion object {
        const val PAGE_STEP = 20
        const val INITIAL_PAGE = 0
    }

    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(PAGE_STEP) ?: page.nextKey?.minus(INITIAL_PAGE)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        val page = params.key ?: INITIAL_PAGE
        val response = api.getReviewsList(
            ReviewsApi.FILTER_REVIEWS,
            ReviewsApi.API_KEY,
            page
        )
        return if (response.isSuccessful) {
            val news = checkNotNull(response.body())
            mapper.mapResponse(page,news)
        } else {
            LoadResult.Error(HttpException(response))
        }
    }

}