package com.example.newsproject.presentation.toolbar

import androidx.appcompat.widget.Toolbar

interface ToolbarHandler {
    fun setupToolbar(toolbar: Toolbar)
    fun setTitle(title: String)
}