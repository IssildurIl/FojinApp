package com.iish.fojinapp.api.dto

import com.google.gson.annotations.SerializedName

data class ReviewDto(
    @field:SerializedName("byline") val byline: String?,
    @field:SerializedName("critics_pick") val criticsPick: Int?,
    @field:SerializedName("date_updated") val dateUpdated: String?,
    @field:SerializedName("display_title") val displayTitle: String?,
    @field:SerializedName("headline") val headline: String?,
    @field:SerializedName("link") val link: LinkDto?,
    @field:SerializedName("mpaa_rating") val rating: String?,
    @field:SerializedName("multimedia") val multimedia: MultimediaDto?,
    @field:SerializedName("opening_date") val openingDate: String?,
    @field:SerializedName("publication_date") val publicationDate: String?,
    @field:SerializedName("summary_short") val summaryShort: String?
)