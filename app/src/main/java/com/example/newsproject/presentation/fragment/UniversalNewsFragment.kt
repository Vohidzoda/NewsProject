package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.mapper.toUiModel
import com.example.domain.model.NewsArticle
import com.example.newsproject.R
import com.example.newsproject.presentation.NewsEvent
import com.example.newsproject.presentation.adapter.NewsAdapter
import com.example.newsproject.presentation.dialog.showDeleteConfirmationDialog
import com.example.newsproject.presentation.swipe.factory.SwipeHandlerFactory
import com.example.newsproject.presentation.swipe.handler.SwipeHandler
import com.example.newsproject.presentation.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UniversalNewsFragment : Fragment(R.layout.fragment_universal_news) {



    @Inject lateinit var swipeHandlerFactory: SwipeHandlerFactory
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter
    private var swipeHandler: SwipeHandler? = null



    companion object {
        fun newInstance(category: String): UniversalNewsFragment {
            return UniversalNewsFragment().apply {
                arguments = Bundle().apply {
                    putString("category", category)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(view)
        observeUiState()

        val category = arguments?.getString("category") ?: "general"
        if (category == "history") {
            viewModel.onEvent(NewsEvent.LoadHistory)
        } else {
            viewModel.onEvent(NewsEvent.LoadCategory(category))
        }
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.newsRecyclerView)
        val currentCategory = arguments?.getString("category") ?: "general"

        newsAdapter = NewsAdapter { article ->
            navigateToDetailFragment(article)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = newsAdapter

        swipeHandler = swipeHandlerFactory.create(currentCategory)
        swipeHandler?.apply {
            setOnSwipeListener { position ->
                val article = newsAdapter.getItemAt(position)
                showDeleteConfirmationDialog(
                    context = requireContext(),
                    onConfirm = {
                        viewModel.onEvent(NewsEvent.DeleteFromHistory(article))
                    },
                    onCancel = {
                        newsAdapter.notifyItemChanged(position)
                    }
                )
            }
            attachToRecyclerView(recyclerView)
        }
    }
    private fun navigateToDetailFragment(article: NewsArticle) {
        val navController = requireActivity().findNavController(R.id.nav_host_fragment)
        val bundle = Bundle().apply {
            putParcelable("news_article", article.toUiModel())
        }
        navController.navigate(R.id.newsDetailFragment, bundle)
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            viewModel.uiState.collectLatest { state ->
                if (state.error != null) {
                }
                newsAdapter.submitList(state.news)
            }
        }
    }
}
