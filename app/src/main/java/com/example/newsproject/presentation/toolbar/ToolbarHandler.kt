package com.example.newsproject.presentation.toolbar

import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.newsproject.presentation.toolbar.menu.MenuItemAction

interface ToolbarHandler {
    fun setupToolbar(activity: AppCompatActivity, toolbar: Toolbar, actions: List<MenuItemAction>)
    fun setTitle(title: String)
    fun enableBackButton(enabled: Boolean)
}