package com.example.newsproject.presentation.toolbar.menu

import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.newsproject.R
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class SearchMenuItemAction @Inject constructor(
    private val context: Context
): MenuItemAction {
    fun onCreateOptionsMenu(menu: Menu) {
        val searchItem = menu.findItem(R.id.search)
        searchItem.title = "Поиск"
    }

    override fun onCreateOptionsMenu(
        menu: Menu,
        menuInflater: MenuInflater
    ) {
        TODO("Not yet implemented")
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.search -> {

                true
            }
            else -> false
        }
    }
}
