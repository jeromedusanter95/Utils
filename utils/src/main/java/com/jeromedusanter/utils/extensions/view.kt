package com.jeromedusanter.utils.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * For activity use : findViewById(android.R.id.content) as view
 */

fun View?.showSnackBar(message: String) {
    this?.let { view ->
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}

fun View?.showSnackBar(messageId: Int) {
    this?.let { view ->
        Snackbar.make(view, messageId, Snackbar.LENGTH_SHORT).show()
    }
}