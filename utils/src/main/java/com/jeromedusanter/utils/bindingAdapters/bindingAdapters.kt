package com.jeromedusanter.utils.bindingAdapters

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.jeromedusanter.utils.extensions.tryCatch

@BindingAdapter("bind:visibleOrGone")
fun setVisibleOrGone(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("bind:visibleOrInvisible")
fun setVisibleOrInvisible(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("bind:textId")
fun setTextById(view: TextView, resId: Int) {
    tryCatch { view.setText(resId) }
}

@BindingAdapter("bind:srcId")
internal fun setSrcWithResId(view: ImageView, @DrawableRes resId: Int) {
    tryCatch { view.setImageResource(resId) }
}

@BindingAdapter("bind:onLongClick")
fun setOnLongClick(
    view: View,
    block: () -> Unit,
) {
    view.setOnLongClickListener {
        block.invoke()
        return@setOnLongClickListener true
    }
}

@BindingAdapter("bind:enabled")
fun setEnabled(view: View, isEnabled: Boolean) {
    view.isEnabled = isEnabled
}

@BindingAdapter("bind:tintId")
fun setTintWithResId(view: ImageView, @ColorRes colorId: Int) {
    tryCatch {
        view.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(view.context, colorId))
    }
}

@BindingAdapter("bind:backgroundColorId")
fun setBackgroundColorWithResId(view: View, @ColorRes colorId: Int) {
    tryCatch { view.setBackgroundColor(ContextCompat.getColor(view.context, colorId)) }
}

@BindingAdapter("bind:indicateColorId")
fun setIndicateColorWithResId(view: CircularProgressIndicator, @ColorRes colorId: Int) {
    tryCatch { view.setIndicatorColor(ContextCompat.getColor(view.context, colorId)) }
}