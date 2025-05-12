package com.example.newsproject.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.NewsArticle
import com.example.newsproject.R
import com.google.android.material.imageview.ShapeableImageView

class NewsAdapter(
    private val onItemClick: (NewsArticle) -> Unit
) : ListAdapter<NewsArticle, NewsAdapter.NewsViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun getItemAt(position: Int): NewsArticle {
        return getItem(position)
    }

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextViewV)
        private val imageView: ShapeableImageView = view.findViewById(R.id.imageCardView)

        fun bind(article: NewsArticle) {
            titleTextView.text = article.title
            descriptionTextView.text = article.description

            Glide.with(imageView.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.plugin)
                .into(imageView)

            itemView.setOnClickListener { onItemClick(article) }
        }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<NewsArticle>() {
            override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle) =
                oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle) =
                oldItem == newItem
        }
    }
}
