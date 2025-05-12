package com.example.newsproject.presentation.toolbar

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuProvider
import com.example.newsproject.presentation.toolbar.menu.MenuItemAction
import javax.inject.Inject

class ToolbarManager @Inject constructor() : ToolbarHandler {

    private var activity: AppCompatActivity? = null
    private var menuProvider: MenuProvider? = null

    override fun setupToolbar(activity: AppCompatActivity, toolbar: Toolbar, actions: List<MenuItemAction>) {
        this.activity = activity
        activity.setSupportActionBar(toolbar)

        menuProvider?.let { activity.removeMenuProvider(it) }

        menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                actions.forEach { it.onCreateOptionsMenu(menu, menuInflater) }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return actions.any { it.onOptionsItemSelected(menuItem) }
            }
        }

        activity.addMenuProvider(menuProvider!!, activity)
    }

    override fun setTitle(title: String) {
        activity?.supportActionBar?.title = title
    }

    override fun enableBackButton(enabled: Boolean) {
        activity?.supportActionBar?.setDisplayHomeAsUpEnabled(enabled)
    }
}