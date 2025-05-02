package com.example.newsproject.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsproject.presentation.fragment.UniversalNewsFragment

class ViewPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    private val categories = listOf("politics", "sport", "business", "science")

    override fun getItemCount(): Int = categories.size

    override fun createFragment(position: Int): Fragment {
        return UniversalNewsFragment.newInstance(categories[position])
    }
}