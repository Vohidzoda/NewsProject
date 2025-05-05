package com.example.newsproject.presentation.swipe.factory

import com.example.newsproject.presentation.swipe.handler.SwipeHandler

interface SwipeHandlerFactory {
    fun create(category: String): SwipeHandler?
}

