package com.example.newsproject.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsproject.R
import com.example.newsproject.presentation.fragment.UniversalNewsFragment

class ViewPagerAdapter(
    fragment: Fragment,
    private val categories: List<String> = listOf(
        fragment.getString(R.string.politics),
        fragment.getString(R.string.sport),
        fragment.getString(R.string.business),
        fragment.getString(R.string.science)
    )
) : FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = categories.size

    override fun createFragment(position: Int): Fragment {
        return UniversalNewsFragment.newInstance(categories[position])
    }

    override fun getItemId(position: Int): Long {
        return categories[position].hashCode().toLong()
    }

    override fun containsItem(itemId: Long): Boolean {
        return categories.any { it.hashCode().toLong() == itemId }
    }
}