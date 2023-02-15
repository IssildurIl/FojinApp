package com.iish.fojinapp.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Review(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val descriptionShort: String,
    val byline: String?,
    val criticsPick: Int?,
    val dateUpdated: String?,
    val headline: String?,
    val link: Link?,
    val rating: String?,
    val multimedia: Multimedia?,
    val openingDate: String?,
    val publicationDate: String?,
    val summaryShort: String?
) : Parcelable

@Parcelize
data class MovieReviews(
    val hasMore: Boolean? = false,
    val numResults: Long?,
    val reviews: List<Review>?
) : Parcelable