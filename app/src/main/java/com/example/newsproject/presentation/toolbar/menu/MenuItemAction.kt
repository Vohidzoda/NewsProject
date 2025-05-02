package com.example.newsproject.presentation.toolbar.menu

interface MenuItemAction {

    fun handle(itemId: Int): Boolean
    fun getMenuItems(): List<Int>
}