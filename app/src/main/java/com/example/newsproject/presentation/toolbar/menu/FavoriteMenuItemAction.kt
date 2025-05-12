package com.example.newsproject.presentation.toolbar.menu

import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.OnBackPressedDispatcher
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.NewsArticle
import com.example.newsproject.R
import com.example.newsproject.presentation.viewModel.NewsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteMenuItemAction @Inject constructor(
    private val context: Context,
    private val viewModel: NewsViewModel,
    private val newsArticle: NewsArticle,
    private val lifecycleOwner: LifecycleOwner,
    private val onBackPressedDispatcher: OnBackPressedDispatcher

) : MenuProvider {

    private lateinit var favoriteMenuItem: MenuItem

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_detail, menu)
        favoriteMenuItem = menu.findItem(R.id.action_favorite)

        viewModel.loadFavoriteStatus(newsArticle.url)

        lifecycleOwner.lifecycleScope.launch {
            viewModel.currentFavoriteState.collect { isFavorite ->
                updateFavoriteIcon(isFavorite)
            }
        }
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_favorite -> {
                viewModel.toggleFavorite(newsArticle)
                true
            }
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> false
        }
    }


    private fun updateFavoriteIcon(isFavorite: Boolean) {
        favoriteMenuItem.icon = ContextCompat.getDrawable(
            context,
            if (isFavorite) R.drawable.ic_star_filled else R.drawable.ic_star_border
        )
    }
}