package com.example.newsproject.presentation.toolbar.menu

import android.content.Context
import android.widget.Toast
import com.example.newsproject.R
import javax.inject.Inject

class NotificationMenuItemAction @Inject constructor(
    private val context: Context
) : MenuItemAction {

    override fun handle(itemId: Int): Boolean {
        return if (itemId == R.id.notification) {
            Toast.makeText(context, "Уведомление нажато", Toast.LENGTH_SHORT).show()
            true
        } else false
    }

    override fun getMenuItems(): List<Int> = listOf(R.id.notification)
}
