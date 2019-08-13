package com.merseyside.admin.treeapp.treeView.utils

import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("bind:colorBackground")
fun setBackground(view: View, @ColorRes color: Int) {
    view.setBackgroundColor(ContextCompat.getColor(view.context, color))
}