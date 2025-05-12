package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.data.mapper.toDomain
import com.example.domain.model.NewsArticle
import com.example.domain.model.usecase.history.InsertHistoryUseCase
import com.example.newsproject.R
import com.example.newsproject.presentation.handler.UrlHandler
import com.example.newsproject.presentation.toolbar.ToolbarHandler
import com.example.newsproject.presentation.toolbar.menu.FavoriteMenuItemAction
import com.example.newsproject.presentation.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsDetailFragment : Fragment(R.layout.fragment_news_detail) {

    @Inject lateinit var insertHistoryUseCase: InsertHistoryUseCase
    @Inject lateinit var toolbarHandler: ToolbarHandler
    @Inject lateinit var urlHandler: UrlHandler

    private val args by navArgs<NewsDetailFragmentArgs>()
    private val viewModel: NewsViewModel by viewModels()

    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var linkTextView: TextView

    private lateinit var newsDomain: NewsArticle

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsUi = args.newsArticle
        newsDomain = newsUi.toDomain()

        toolbarHandler.setTitle(getString(R.string.news_details_title))
        toolbarHandler.enableBackButton(true)


        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.arrow_back)
        }

        titleTextView = view.findViewById(R.id.newsTitle)
        descriptionTextView = view.findViewById(R.id.newsDescription)
        imageView = view.findViewById(R.id.newsImage)
        linkTextView = view.findViewById(R.id.newsLink)

        titleTextView.text = newsUi.title
        descriptionTextView.text = newsUi.description

        Glide.with(imageView.context)
            .load(newsUi.urlToImage)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.plugin)
            .centerCrop()
            .into(imageView)

        linkTextView.text = newsUi.url
        linkTextView.setOnClickListener {
            if (newsUi.url.isNotBlank()) {
                urlHandler.openUrl(newsUi.url)
            } else {
                Toast.makeText(requireContext(), "Некорректная ссылка", Toast.LENGTH_SHORT).show()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            insertHistoryUseCase(newsDomain)
        }


        requireActivity().addMenuProvider(
            FavoriteMenuItemAction(
                context = requireContext(),
                viewModel = viewModel,
                newsArticle = newsDomain,
                lifecycleOwner = viewLifecycleOwner,
                onBackPressedDispatcher = requireActivity().onBackPressedDispatcher

            ),
            viewLifecycleOwner
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        toolbarHandler.setTitle(getString(R.string.app_name))
        toolbarHandler.enableBackButton(false)
    }
}
