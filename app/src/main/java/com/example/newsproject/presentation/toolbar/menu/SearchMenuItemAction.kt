package com.example.newsproject.presentation.toolbar.menu

import android.content.Context
import android.widget.Toast
import com.example.newsproject.R
import javax.inject.Inject

class SearchMenuItemAction @Inject constructor(
    private val context: Context
) : MenuItemAction {

    override fun handle(itemId: Int): Boolean {
        return if (itemId == R.id.search) {
            Toast.makeText(context, "Поиск нажат", Toast.LENGTH_SHORT).show()
            true
        } else false
    }

    override fun getMenuItems(): List<Int> = listOf(R.id.search)
}
