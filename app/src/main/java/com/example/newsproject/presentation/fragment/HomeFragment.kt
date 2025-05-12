package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.newsproject.MainActivity
import com.example.newsproject.R
import com.example.newsproject.presentation.adapter.ViewPagerAdapter
import com.example.newsproject.presentation.toolbar.ToolbarHandler
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    @Inject lateinit var toolbarHandler: ToolbarHandler
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarHandler.setTitle(getString(R.string.home))

        viewPager = view.findViewById(R.id.viewPager2)
        tabLayout = view.findViewById(R.id.tabLayout)

        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> tabLayout.context.getString(R.string.tab_politics)
                1 -> tabLayout.context.getString(R.string.tab_sport)
                2 -> tabLayout.context.getString(R.string.tab_business)
                3 -> tabLayout.context.getString(R.string.tab_science)
                else -> ""
            }
        }.attach()
    }

}