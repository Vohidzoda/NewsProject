package com.example.newsproject.presentation.toolbar

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.newsproject.R
import com.example.newsproject.presentation.toolbar.menu.MenuItemAction
import javax.inject.Inject

class ToolbarManager @Inject constructor(
    private val context: Context,
    private val actions: Set<@JvmSuppressWildcards MenuItemAction>
) : ToolbarHandler {

    override fun setupToolbar(toolbar: Toolbar) {
        toolbar.title = context.getString(R.string.app_name)
        toolbar.menu.clear()
        toolbar.inflateMenu(R.menu.main_menu)

        toolbar.setOnMenuItemClickListener { item ->
            actions.any { it.handle(item.itemId) }
        }
    }

    override fun setTitle(title: String) {
        (context as? AppCompatActivity)?.supportActionBar?.title = title
    }
}
