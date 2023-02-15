package com.iish.fojinapp.ui.adapter.review

import androidx.recyclerview.widget.DiffUtil
import com.iish.fojinapp.domain.entity.Review

class ReviewComparator: DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Review, newItem: Review) = oldItem == newItem
}