package com.example.newsproject.presentation.navigator

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.example.data.mapper.toUiModel
import com.example.domain.model.NewsArticle
import com.example.newsproject.R

class NewsNavigatorImpl(private val activity: FragmentActivity) : NewsNavigator {

    override fun navigateToDetail(article: NewsArticle) {
        val navController = activity.findNavController(R.id.nav_host_fragment)
        val bundle = Bundle().apply {
            putParcelable("news_article", article.toUiModel())
        }
        navController.navigate(R.id.newsDetailFragment, bundle)
    }
}
