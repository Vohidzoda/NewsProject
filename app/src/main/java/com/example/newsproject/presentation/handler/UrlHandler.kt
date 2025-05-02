package com.example.newsproject.presentation.handler

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import javax.inject.Inject

class UrlHandler @Inject constructor(private val context: Context) {

    fun openUrl(url: String) {
        val validUrl = when {
            url.startsWith("http") -> url
            url.isNotBlank() -> "https://$url"
            else -> {
                Toast.makeText(context, "Некорректный URL", Toast.LENGTH_SHORT).show()
                return
            }
        }

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(validUrl)).apply {
            if (context !is Activity) {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }

        val browsers = context.packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL)
        if (browsers.isNotEmpty()) {
            val chooser = Intent.createChooser(intent, "Выберите браузер").apply {
                if (context !is Activity) {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                putExtra(Intent.EXTRA_INITIAL_INTENTS, browsers.map {
                    Intent(intent).setPackage(it.activityInfo.packageName)
                }.toTypedArray())
            }
            context.startActivity(chooser)
        } else {
            Toast.makeText(context, "Не найдено браузеров", Toast.LENGTH_SHORT).show()
        }
    }
}
