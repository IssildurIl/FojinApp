package com.iish.fojinapp.ui.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iish.fojinapp.databinding.FragmentReviewsBinding
import com.iish.fojinapp.ui.adapter.SpacingItemDecoration
import com.iish.fojinapp.ui.adapter.StateAdapter
import com.iish.fojinapp.ui.adapter.review.ReviewsAdapter
import com.iish.fojinapp.ui.viewmodels.ReviewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewsFragment : Fragment() {

    private var _binding: FragmentReviewsBinding? = null
    private val binding get() = _binding!!

    private val reviewsViewModel: ReviewsViewModel by viewModels()

    private val decorator by lazy { SpacingItemDecoration(5, 5) }
    private val adapter by lazy { ReviewsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            listReviews.addItemDecoration(decorator)
            listReviews.adapter = adapter.withLoadStateHeaderAndFooter(
                header = StateAdapter(),
                footer = StateAdapter { adapter.retry() }
            )
            reviewsViewModel.getReview.observe(viewLifecycleOwner) { reviews ->
                adapter.submitData(lifecycle, reviews)
                reviewsViewModel.insertReviews(adapter.snapshot().items)
            }
            listReviews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastPosition = layoutManager.findLastVisibleItemPosition()
                    if (lastPosition == adapter.itemCount.minus(1)) {
                        reviewsViewModel.incrementPage()
                    }
                }
            })

            searchReviews.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    reviewsViewModel.searchReview(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }

            })
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}