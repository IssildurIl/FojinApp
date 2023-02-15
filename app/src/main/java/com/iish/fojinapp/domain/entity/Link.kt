package com.iish.fojinapp.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Link(
    val type: String?,
    val urlPage: String?,
    val suggestedLinkText: String?
) : Parcelable