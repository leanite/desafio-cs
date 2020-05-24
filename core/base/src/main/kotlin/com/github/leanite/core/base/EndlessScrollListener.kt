package com.github.leanite.core.base

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndlessScrollListener(private val loadMoreItems: () -> Unit) : RecyclerView.OnScrollListener() {
    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }
    private var previousTotal: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0
    private var firstVisibleItem: Int = 0
    private var loading: Boolean = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        updateVisibleItems(recyclerView)
        decideIfCanStopLoading()
        decideIfCanLoadMoreItems()
    }

    private fun updateVisibleItems(recyclerView: RecyclerView) {
        visibleItemCount = recyclerView.childCount
        totalItemCount = recyclerView.layoutManager?.itemCount ?: 0
        firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
    }

    private fun decideIfCanStopLoading() {
        if (shouldStopLoading()) {
            loading = false
            previousTotal = totalItemCount
        }
    }

    private fun shouldStopLoading(): Boolean = loading && totalItemCount > previousTotal

    private fun decideIfCanLoadMoreItems() {
        if (shouldLoadMoreItems()) {
            loading = true
            loadMoreItems()
        }
    }

    private fun shouldLoadMoreItems(): Boolean =
        !loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + VISIBLE_THRESHOLD)

    fun reset() {
        previousTotal = 0
        loading = true
    }
}
