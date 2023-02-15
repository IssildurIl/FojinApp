package com.iish.fojinapp.ui.adapter.review

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Browser
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iish.fojinapp.databinding.ItemReviewBinding
import com.iish.fojinapp.domain.entity.Review


class ReviewsAdapter: PagingDataAdapter<Review, ReviewsAdapter.ReviewItem>(ReviewComparator()) {

    override fun onBindViewHolder(holder: ReviewItem, position: Int) =
        holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ReviewItem(LayoutInflater.from(parent.context), parent)

    class ReviewItem internal constructor(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            operator fun invoke(
                layoutInflater: LayoutInflater,
                parent: ViewGroup? = null,
                attachToRoot: Boolean = false
            ): ReviewItem {
                return ReviewItem(ItemReviewBinding.inflate(layoutInflater, parent, attachToRoot))
            }
        }

        fun bind(review: Review?) {
            review?.let {
                with(binding) {
                    Glide.with(root).load(review.multimedia?.srcUrl).centerCrop().into(titleImage)
                    title.text = review.title
                    descriptionShort.text = review.descriptionShort
                    time.text = review.publicationDate
                    link.setOnClickListener{
                        val uri: Uri = Uri.parse(review.link?.urlPage)
                        val context: Context = binding.root.context
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.putExtra(Browser.EXTRA_APPLICATION_ID, context.packageName)
                        context.startActivity(intent);
                    }
                }
            }
        }
    }
}