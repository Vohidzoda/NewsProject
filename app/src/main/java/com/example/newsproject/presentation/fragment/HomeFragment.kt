package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.newsproject.R
import com.example.newsproject.presentation.adapter.ViewPagerAdapter
import com.example.newsproject.presentation.toolbar.ToolbarHandler
import com.example.newsproject.presentation.viewModel.HomeViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    @Inject lateinit var toolbarHandler: ToolbarHandler
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarHandler.setTitle(getString(R.string.home))

        viewPager = view.findViewById(R.id.viewPager2)
        tabLayout = view.findViewById(R.id.tabLayout)

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshCategories()
    }


    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.enabledCategories.collect { categories ->
                setupTabs(categories)
            }
        }
    }

    private fun setupTabs(categories: List<String>) {
        val adapter = ViewPagerAdapter(this, categories)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (categories[position]) {
                "politics" -> getString(R.string.tab_politics)
                "sport" -> getString(R.string.tab_sport)
                "business" -> getString(R.string.tab_business)
                "science" -> getString(R.string.tab_science)
                else -> ""
            }
        }.attach()
    }

}
