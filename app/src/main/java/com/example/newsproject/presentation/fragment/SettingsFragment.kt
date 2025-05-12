package com.example.newsproject.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.newsproject.MainActivity
import com.example.newsproject.R
import com.example.newsproject.presentation.toolbar.ToolbarHandler
import com.example.newsproject.presentation.toolbar.ToolbarManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    @Inject lateinit var toolbarHandler: ToolbarHandler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarHandler.setTitle(getString(R.string.settings))
    }


}