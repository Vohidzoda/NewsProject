package com.example.newsproject.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newsproject.R
import com.example.newsproject.presentation.handler.UrlHandler
import com.example.newsproject.presentation.toolbar.ToolbarHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsDetailFragment : Fragment(R.layout.fragment_news_detail) {

    @Inject lateinit var toolbarHandler: ToolbarHandler
    @Inject lateinit var urlHandler: UrlHandler


    private val args by navArgs<NewsDetailFragmentArgs>()
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var linkTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarHandler.setTitle("Детали новости")
        toolbarHandler.enableBackButton(true)
        setHasOptionsMenu(true)

        val activity = activity as? AppCompatActivity
        activity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity?.supportActionBar?.setHomeAsUpIndicator(R.drawable.arrow_back)

        titleTextView = view.findViewById(R.id.newsTitle)
        descriptionTextView = view.findViewById(R.id.newsDescription)
        imageView = view.findViewById(R.id.newsImage)
        linkTextView = view.findViewById(R.id.newsLink)

        val newsArticle = args.newsArticle

        titleTextView.text = newsArticle.title
        descriptionTextView.text = newsArticle.description

        Glide.with(imageView.context)
            .load(newsArticle.urlToImage)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.plugin)
            .centerCrop()
            .into(imageView)

        val linkTextView: TextView = view.findViewById(R.id.newsLink)
        linkTextView.text = newsArticle.url
        linkTextView.setOnClickListener {
            val url = newsArticle.url
            println("Clicked URL: $url")
            if (url.isNotBlank()) {
                urlHandler.openUrl(url)
            } else {
                Toast.makeText(requireContext(), "Некорректная ссылка", Toast.LENGTH_SHORT).show()
            }
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()

        val activity = activity as? AppCompatActivity
        activity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        toolbarHandler.setTitle(getString(R.string.app_name))
        toolbarHandler.enableBackButton(false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
