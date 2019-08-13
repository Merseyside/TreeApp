package com.merseyside.admin.treeapp.treeView.model

import Level
import androidx.annotation.ColorRes
import androidx.databinding.Bindable
import com.merseyside.admin.treeapp.BR
import com.merseyside.admin.treeapp.R
import com.upstream.basemvvmimpl.presentation.model.BaseAdapterViewModel

class NodeItemViewModel<T>(private var node: Pair<Node<T>, Level>) : BaseAdapterViewModel<Pair<Node<T>, Level>>() {

    var isSelected = false
    set(value) {
        field = value

        notifyPropertyChanged(BR.nodeBackground)
    }

    override fun getItem(): Pair<Node<T>, Level> {
        return node
    }

    override fun setItem(item: Pair<Node<T>, Level>) {
        this.node = item
    }

    @Bindable
    fun getValue(): String {
        val stringBuilder = StringBuilder()

        repeat((0 until node.second).count()) {
            stringBuilder.append("    ")
        }

        return stringBuilder.append(node.first.value).toString()
    }

    fun onClick() {
        if (node.first.isNotDeleted()) {
            isSelected = !isSelected
        }
    }

    @Bindable
    @ColorRes
    fun getNodeBackground(): Int {

        return if (node.first.isNotDeleted()) {
            when (isSelected) {
                true -> {
                    R.color.grey
                }
                false -> {
                    R.color.transparent
                }
            }
        } else {
            R.color.colorAccent
        }
    }
}