package com.example.newsproject.presentation.toolbar.menu

import android.content.Context
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.newsproject.R
import javax.inject.Inject

class NotificationMenuItemAction @Inject constructor(
    private val context: Context
) : MenuItemAction {
    var onNotificationClicked: (() -> Unit)? = null

    init {
        Log.d("NotificationsMenuAction", "NotificationsMenuAction initialized!")
    }

    fun onCreateOptionsMenu(menu: Menu) {
    }

    override fun onCreateOptionsMenu(
        menu: Menu,
        menuInflater: MenuInflater
    ) {
        TODO("Not yet implemented")
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.notification -> {
                Log.d("NotificationsMenuAction", "Notification clicked!")
                onNotificationClicked?.invoke()
                true
            }
            else -> false
        }
    }

}