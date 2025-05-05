package com.example.newsproject.presentation.swipe.handler

import androidx.recyclerview.widget.RecyclerView

interface SwipeHandler {
    fun attachToRecyclerView(recyclerView: RecyclerView)
    fun setOnSwipeListener(listener: (position: Int) -> Unit)
}