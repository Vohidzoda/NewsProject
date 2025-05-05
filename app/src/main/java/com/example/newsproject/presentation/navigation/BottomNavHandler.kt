package com.example.newsproject.presentation.navigation

import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView


interface BottomNavHandler {
    fun setup(navController: NavController)
    fun navigate(itemId: Int): Boolean
}