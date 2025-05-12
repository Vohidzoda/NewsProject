package com.example.newsproject.presentation.toolbar.menu

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

interface MenuItemAction {
    fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater)
    fun onOptionsItemSelected(menuItem: MenuItem): Boolean
}