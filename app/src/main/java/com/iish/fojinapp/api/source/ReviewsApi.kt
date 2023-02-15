package com.iish.fojinapp.api.source

import com.iish.fojinapp.BuildConfig
import com.iish.fojinapp.api.dto.ReviewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewsApi {

    companion object {
        const val FILTER_REVIEWS = "all"
        var API_KEY: String = BuildConfig.API_KEY
    }

    @GET("svc/movies/v2/reviews/{filter}.json")
    suspend fun getReviewsList(
        @Path("filter") filterCritics: String,
        @Query("api-key") apiKey: String,
        @Query("page") @androidx.annotation.IntRange(from = 0) page: Int = 0
    ): Response<ReviewsResponse>


}