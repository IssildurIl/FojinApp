package com.iish.fojinapp.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Multimedia(
    val type: String?,
    val srcUrl: String?,
    val heightSrc: Int?,
    val width: Int?
) : Parcelable