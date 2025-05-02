package com.example.newsproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.newsproject.presentation.fragment.HomeFragment
import com.example.newsproject.presentation.toolbar.ToolbarManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var toolbarManager: ToolbarManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbarUniversal)
        toolbarManager.setupToolbar(toolbar)

    }
}
