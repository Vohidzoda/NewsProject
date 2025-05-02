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
import com.example.newsproject.R
import com.example.newsproject.presentation.adapter.NewsAdapter
import com.example.newsproject.presentation.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class UniversalNewsFragment : Fragment(R.layout.fragment_universal_news) {

    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

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

        val recyclerView = view.findViewById<RecyclerView>(R.id.newsRecyclerView)

        newsAdapter = NewsAdapter { article ->
            val uiModel = article.toUiModel()

            val navController = requireActivity().findNavController(R.id.nav_host_fragment)

            val bundle = Bundle().apply {
                putParcelable("news_article", uiModel)
            }

            navController.navigate(R.id.newsDetailFragment, bundle)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }

        val category = arguments?.getString("category") ?: "general"
        viewModel.loadNews(category)

        observeNews()
    }

    private fun observeNews() {
        lifecycleScope.launchWhenStarted {
            viewModel.newsList.collectLatest { articles ->
                newsAdapter.submitList(articles)
            }
        }
    }
}