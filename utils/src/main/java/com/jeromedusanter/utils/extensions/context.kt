package com.jeromedusanter.utils.extensions

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun Context?.showKeyboard(view: View?) {
    if (view != null) {
        val imm = this?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.toggleSoftInputFromWindow(view.windowToken, 0, 0)
    }
}

fun Context.hideKeyboard(view: View?) {
    if (view != null) {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Context?.showToast(
    message: String,
    gravity: Int = Gravity.BOTTOM,
    duration: Int = Toast.LENGTH_SHORT,
) {
    this?.let { context ->
        Toast.makeText(context, message, duration)
            .apply { setGravity(gravity, 0, 0) }
            .show()
    }
}

fun Context?.showToast(
    @StringRes messageId: Int,
    gravity: Int = Gravity.BOTTOM,
    duration: Int = Toast.LENGTH_SHORT,
) {
    this?.let { context ->
        Toast.makeText(context, context.getString(messageId), duration)
            .apply { setGravity(gravity, 0, 0) }
            .show()
    }
}