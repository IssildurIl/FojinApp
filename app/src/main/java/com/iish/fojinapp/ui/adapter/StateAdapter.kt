package com.iish.fojinapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iish.fojinapp.databinding.ItemErrorStateBinding
import com.iish.fojinapp.databinding.ItemLoadStateBinding

class StateAdapter(private val retry: (() -> Unit)? = null) : LoadStateAdapter<StateAdapter.StateViewHolder>() {

    override fun getStateViewType(loadState: LoadState) = when (loadState) {
        is LoadState.NotLoading -> error("Not supporting")
        LoadState.Loading -> PROGRESS
        is LoadState.Error -> ERROR
    }

    private companion object {

        private const val ERROR = 1
        private const val PROGRESS = 0
    }

    override fun onBindViewHolder(holder: StateViewHolder, loadState: LoadState) {
        holder.bind(loadState, retry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): StateViewHolder {
        return when (loadState) {
            LoadState.Loading -> LoadStateHolder(LayoutInflater.from(parent.context), parent)
            is LoadState.Error -> ErrorStateHolder(LayoutInflater.from(parent.context), parent)
            is LoadState.NotLoading -> error("Not supporting")
        }
    }

    abstract class StateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(loadState: LoadState, retry: (() -> Unit)? = null)
    }

    class LoadStateHolder internal constructor(private val binding: ItemLoadStateBinding) :
        StateViewHolder(binding.root) {

        companion object {
            operator fun invoke(
                layoutInflater: LayoutInflater,
                parent: ViewGroup? = null,
                attachToRoot: Boolean = false
            ): LoadStateHolder {
                return LoadStateHolder(
                    ItemLoadStateBinding.inflate(
                        layoutInflater,
                        parent,
                        attachToRoot
                    )
                )
            }
        }

        override fun bind(loadState: LoadState, retry: (() -> Unit)?) {
            // TODO: bind
        }
    }

    class ErrorStateHolder internal constructor(private val binding: ItemErrorStateBinding) :
        StateViewHolder(binding.root) {

        companion object {
            operator fun invoke(
                layoutInflater: LayoutInflater,
                parent: ViewGroup? = null,
                attachToRoot: Boolean = false
            ): ErrorStateHolder {
                return ErrorStateHolder(
                    ItemErrorStateBinding.inflate(
                        layoutInflater,
                        parent,
                        attachToRoot
                    )
                )
            }
        }

        override fun bind(loadState: LoadState, retry: (() -> Unit)?) {
            with(binding) {
                if (loadState is LoadState.Error) {
                    errorMessage.text = loadState.error.localizedMessage
                    retryLoad.setOnClickListener { retry?.invoke() }
                }
            }
        }

    }
}