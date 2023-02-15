package com.iish.fojinapp.api.dto

import com.google.gson.annotations.SerializedName

data class MultimediaDto(
    @field:SerializedName("type") val type: String?,
    @field:SerializedName("src") val srcUrl: String?,
    @field:SerializedName("height") val heightSrc: Int?,
    @field:SerializedName("width") val width: Int?
)