package com.iish.fojinapp.api.repository

import com.iish.fojinapp.api.source.ReviewsApi
import javax.inject.Inject

class CriticsRepository @Inject constructor(
    private var reviewsApi: ReviewsApi
) {

}