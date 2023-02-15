package com.iish.fojinapp.repository.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "review")
class ReviewCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: UUID,

    @ColumnInfo(name = "byline")
    var byline: String?,

    @ColumnInfo(name = "critics_pick")
    val criticsPick: Int?,

    @ColumnInfo(name = "date_updated")
    val dateUpdated: String?,

    @ColumnInfo(name = "display_title")
    val displayTitle: String?,

    @ColumnInfo(name = "headline")
    val headline: String?,

    @ColumnInfo(name = "link_type")
    val link_type: String?,

    @ColumnInfo(name = "link_url")
    val link_urlPage: String?,

    @ColumnInfo(name = "link_suggested_link_text")
    val link_suggestedLinkText: String?,

    @ColumnInfo(name = "mpaa_rating")
    val rating: String?,

    @ColumnInfo(name = "multimedia_type")
    val multimedia_type: String?,

    @ColumnInfo(name = "multimedia_src")
    val multimedia_srcUrl: String?,

    @ColumnInfo(name = "multimedia_height")
    val multimedia_heightSrc: Int?,

    @ColumnInfo(name = "multimedia_width")
    val multimedia_width: Int?,

    @ColumnInfo(name = "opening_date")
    val openingDate: String?,

    @ColumnInfo(name = "publication_date")
    val publicationDate: String?,

    @ColumnInfo(name = "summary_short")
    val summaryShort: String?
) {
}