package com.example.newsproject.presentation.navigation

import android.os.Bundle
import androidx.navigation.NavController
import com.example.newsproject.R
import javax.inject.Inject

class BottomNavHandlerImpl @Inject constructor() : BottomNavHandler {

    private var navController: NavController? = null

    override fun setup(navController: NavController) {
        this.navController = navController
    }

    override fun navigate(itemId: Int): Boolean {
        return when (itemId) {
            R.id.home_nav -> {
                navController?.let {
                    if (it.currentDestination?.id != R.id.homeFragment) {
                        it.popBackStack(R.id.homeFragment, false)
                    }
                    true
                } ?: false
            }
            R.id.history_nav -> {
                navController?.navigate(
                    R.id.universalNewsFragment,
                    Bundle().apply { putString("category", "history") }
                ) != null
            }
            R.id.star_nav -> {
                navController?.navigate(
                    R.id.universalNewsFragment,
                    Bundle().apply { putString("category", "favorites") }
                ) != null
            }
            R.id.settings_nav -> {
                navController?.navigate(R.id.settingsFragment) != null
            }
            else -> false
        }
    }
}