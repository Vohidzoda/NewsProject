package com.example.newsproject.presentation.swipe.factory

import com.example.newsproject.presentation.swipe.handler.HistorySwipeHandler
import com.example.newsproject.presentation.swipe.handler.SwipeHandler
import javax.inject.Inject

class SwipeHandlerFactoryImpl @Inject constructor(
    private val historySwipeHandler: HistorySwipeHandler
) : SwipeHandlerFactory {
    override fun create(category: String): SwipeHandler? {
        return when (category) {
            "history" -> historySwipeHandler
            else -> null
        }
    }
}