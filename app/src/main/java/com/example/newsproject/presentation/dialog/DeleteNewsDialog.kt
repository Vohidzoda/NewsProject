package com.example.newsproject.presentation.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.newsproject.R

fun showDeleteConfirmationDialog(
    context: Context,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog.Builder(context)
        .setTitle(context.getString(R.string.dialog_delete_title))
        .setMessage(context.getString(R.string.dialog_delete_message))
        .setPositiveButton(context.getString(R.string.dialog_delete_confirm)) { _, _ -> onConfirm() }
        .setNegativeButton(context.getString(R.string.dialog_delete_cancel)) { _, _ -> onCancel() }
        .setCancelable(false)
        .show()
}
