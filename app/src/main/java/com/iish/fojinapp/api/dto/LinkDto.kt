package com.iish.fojinapp.api.dto

import com.google.gson.annotations.SerializedName

data class LinkDto(
    @field:SerializedName("type") val type: String?,
    @field:SerializedName("url") val urlPage: String?,
    @field:SerializedName("suggested_link_text") val suggestedLinkText: String?
)