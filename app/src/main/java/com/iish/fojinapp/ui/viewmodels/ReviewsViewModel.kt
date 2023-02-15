package com.iish.fojinapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.iish.fojinapp.domain.entity.Review
import com.iish.fojinapp.domain.repository.ReviewsRepository
import com.iish.fojinapp.ui.utils.implementation.InsertReviewsImpl
import com.iish.fojinapp.ui.utils.implementation.SearchReviewsImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ReviewsViewModel
@Inject constructor(
    private val reviewsRepository: ReviewsRepository,
    private val searchReviews: SearchReviewsImpl,
    private val insertReviews: InsertReviewsImpl
) : ViewModel() {

    private val review = MutableLiveData<PagingData<Review>>()
    val getReview: LiveData<PagingData<Review>> get() = review

    private val currentPage = MutableLiveData(0)

    private val searchedReview = MutableLiveData<List<Review>>()
    val getSearchedReview: LiveData<List<Review>> get() = searchedReview

    init {
        getReviews()
    }

    private fun getReviews() {
        viewModelScope.launch {
            reviewsRepository.getReviews().cachedIn(viewModelScope).collect { reviews ->
                review.value = reviews
            }
        }
    }

    fun incrementPage() {
        currentPage.value = currentPage.value?.plus(1)
    }

    fun searchReview(query: String?) {
        viewModelScope.launch {
            searchReviews.searchReviews(
                query = query ?: "",
                page = currentPage.value ?: 0
            ).collect { reviews ->
                searchedReview.value = reviews
            }
        }
    }

    fun insertReviews(reviews:List<Review>){
        viewModelScope.launch {
            insertReviews.insertReviews(reviews)
        }
    }

}