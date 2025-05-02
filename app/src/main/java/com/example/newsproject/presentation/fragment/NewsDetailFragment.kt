package com.example.newsproject.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newsproject.R
import com.example.data.model.NewsArticleUiModel

class NewsDetailFragment : Fragment(R.layout.fragment_news_detail) {

    private val args by navArgs<NewsDetailFragmentArgs>()
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var linkTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        linkTextView.text = newsArticle.url
        linkTextView.setOnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(newsArticle.url))
                startActivity(browserIntent)
            } catch (e: Exception) {
            }
        }
    }
}