package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.NewsArticle
import com.example.newsproject.R
import com.example.newsproject.presentation.NewsEvent
import com.example.newsproject.presentation.adapter.NewsAdapter
import com.example.newsproject.presentation.dialog.showDeleteConfirmationDialog
import com.example.newsproject.presentation.navigator.NewsNavigator
import com.example.newsproject.presentation.swipe.factory.SwipeHandlerFactory
import com.example.newsproject.presentation.swipe.handler.SwipeHandler
import com.example.newsproject.presentation.toolbar.ToolbarHandler
import com.example.newsproject.presentation.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UniversalNewsFragment : Fragment(R.layout.fragment_universal_news) {

    @Inject lateinit var newsNavigator: NewsNavigator
    @Inject lateinit var toolbarHandler: ToolbarHandler
    @Inject lateinit var swipeHandlerFactory: SwipeHandlerFactory
    private val viewModel: NewsViewModel by viewModels()

    private lateinit var newsAdapter: NewsAdapter
    private var swipeHandler: SwipeHandler? = null

    private val category: String by lazy {
        arguments?.getString(ARG_CATEGORY) ?: DEFAULT_CATEGORY
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
        observeUiState()
        loadData()
    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    private fun setupToolbar() {
        val title = when (category) {
            "history" -> R.string.history
            "favorites" -> R.string.favorites
            else -> R.string.home
        }
        toolbarHandler.setTitle(getString(title))
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.newsRecyclerView)
        newsAdapter = NewsAdapter(::navigateToDetailFragment)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = newsAdapter

        swipeHandler = swipeHandlerFactory.create(category).apply {
            this?.setOnSwipeListener(::handleSwipe)
            this?.attachToRecyclerView(recyclerView)
        }
    }

    private fun handleSwipe(position: Int) {
        val article = newsAdapter.getItemAt(position)
        showDeleteConfirmationDialog(
            context = requireContext(),
            onConfirm = {
                when (category) {
                    "history" -> viewModel.onEvent(NewsEvent.DeleteFromHistory(article))
                    "favorites" -> viewModel.onEvent(NewsEvent.DeleteFromFavorites(article.url))
                }
            },
            onCancel = {
                newsAdapter.notifyItemChanged(position)
            }
        )
    }

    private fun navigateToDetailFragment(article: NewsArticle) {
        newsNavigator.navigateToDetail(article)
    }


    private fun observeUiState() {
        lifecycleScope.launch {
            viewModel.uiState.collectLatest { state ->
                state.news?.let { newsAdapter.submitList(it) }
            }
        }
    }

    private fun loadData() {
        val event = when (category) {
            "history" -> NewsEvent.LoadHistory
            "favorites" -> NewsEvent.LoadFavorites
            else -> NewsEvent.LoadCategory(category)
        }
        viewModel.onEvent(event)
    }

    companion object {
        private const val ARG_CATEGORY = "category"
        private const val DEFAULT_CATEGORY = "general"

        fun newInstance(category: String) = UniversalNewsFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_CATEGORY, category)
            }
        }
    }
}
