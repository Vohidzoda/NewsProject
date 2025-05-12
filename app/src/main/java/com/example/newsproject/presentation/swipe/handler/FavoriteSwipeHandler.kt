package com.example.newsproject.presentation.swipe.handler

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class FavoriteSwipeHandler @Inject constructor(
    private val context: Context
) : SwipeHandler {

    private var itemTouchHelper: ItemTouchHelper? = null
    private var onSwipeListener: ((Int) -> Unit)? = null

    override fun attachToRecyclerView(recyclerView: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                onSwipeListener?.invoke(viewHolder.adapterPosition)
            }
        }

        itemTouchHelper = ItemTouchHelper(callback).apply {
            attachToRecyclerView(recyclerView)
        }
    }

    override fun setOnSwipeListener(listener: (position: Int) -> Unit) {
        this.onSwipeListener = listener
    }
}
