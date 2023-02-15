package com.iish.fojinapp.repository.cache.mapper

import com.iish.fojinapp.domain.entity.Link
import com.iish.fojinapp.domain.entity.Multimedia
import com.iish.fojinapp.domain.entity.Review
import com.iish.fojinapp.domain.mapper.EntityMapper
import com.iish.fojinapp.repository.cache.model.ReviewCacheEntity
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<ReviewCacheEntity, Review> {


    fun reviewListToEntityList(notes: List<Review>): List<ReviewCacheEntity>{
        val entities: ArrayList<ReviewCacheEntity> = ArrayList()
        for(note in notes){
            entities.add(mapToEntity(note))
        }
        return entities
    }

    fun entityListToReviewList(entities: List<ReviewCacheEntity>): List<Review>{
        val list: ArrayList<Review> = ArrayList()
        for(entity in entities){
            list.add(mapFromEntity(entity))
        }
        return list
    }

    override fun mapFromEntity(entity: ReviewCacheEntity): Review {
        with(entity) {
            return Review(
                id = id,
                title = displayTitle ?: "",
                descriptionShort = summaryShort ?: "",
                byline = byline ?: "",
                criticsPick = criticsPick ?: 0,
                dateUpdated = dateUpdated ?: "",
                headline = headline ?: "",
                link = Link(type = link_type, urlPage = link_urlPage,suggestedLinkText = link_suggestedLinkText),
                rating = rating ?: "",
                multimedia = Multimedia(type = multimedia_type,srcUrl = multimedia_srcUrl, heightSrc = multimedia_heightSrc, width = multimedia_width),
                openingDate = openingDate ?: "",
                publicationDate = publicationDate ?: "",
                summaryShort = summaryShort ?: ""
            )
        }
    }

    override fun mapToEntity(domainModel: Review): ReviewCacheEntity {
        with(domainModel) {
            return ReviewCacheEntity(
                id = id,
                byline = byline ?: "",
                criticsPick = criticsPick ?: 0,
                dateUpdated = dateUpdated ?: "",
                displayTitle = title ?: "",
                headline = headline ?: "",
                link_type = link?.type,
                link_urlPage = link?.urlPage,
                link_suggestedLinkText = link?.suggestedLinkText,
                rating = rating ?: "",
                multimedia_type = multimedia?.type,
                multimedia_srcUrl= multimedia?.srcUrl,
                multimedia_heightSrc = multimedia?.heightSrc,
                multimedia_width = multimedia?.width,
                openingDate = openingDate ?: "",
                publicationDate = publicationDate ?: "",
                summaryShort = descriptionShort ?: ""
            )
        }
    }
}