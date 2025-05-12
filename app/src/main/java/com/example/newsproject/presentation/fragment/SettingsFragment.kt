package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newsproject.R
import com.example.newsproject.presentation.toolbar.ToolbarHandler
import com.example.newsproject.presentation.viewModel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    @Inject lateinit var toolbarHandler: ToolbarHandler
    private val viewModel: SettingsViewModel by viewModels()

    private lateinit var checkPolitics: CheckBox
    private lateinit var checkSport: CheckBox
    private lateinit var checkNature: CheckBox
    private lateinit var checkScience: CheckBox

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarHandler.setTitle(getString(R.string.settings))

        checkPolitics = view.findViewById(R.id.checkPolitics)
        checkSport = view.findViewById(R.id.checkSport)
        checkNature = view.findViewById(R.id.checkNature)
        checkScience = view.findViewById(R.id.checkScience)

        observeViewModel()
        setupListeners()
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.categories.collect { list ->
                list.forEach {
                    when (it.category) {
                        "politics" -> checkPolitics.isChecked = it.isEnabled
                        "sport" -> checkSport.isChecked = it.isEnabled
                        "business" -> checkNature.isChecked = it.isEnabled
                        "science" -> checkScience.isChecked = it.isEnabled
                    }
                }
            }
        }
    }


    private fun setupListeners() {
        checkPolitics.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onCategoryChecked("politics", isChecked)
        }
        checkSport.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onCategoryChecked("sport", isChecked)
        }
        checkNature.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onCategoryChecked("business", isChecked)
        }
        checkScience.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onCategoryChecked("science", isChecked)
        }
    }
}
