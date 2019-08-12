package com.merseyside.admin.treeapp.treeView.model

import Level
import com.upstream.basemvvmimpl.presentation.model.BaseAdapterViewModel

class NodeItemViewModel(private var node: Pair<Node<String>, Level>) : BaseAdapterViewModel<Pair<Node<String>, Level>>() {

    override fun getItem(): Pair<Node<String>, Level> {
        return node
    }

    override fun setItem(item: Pair<Node<String>, Level>) {
        this.node = item
    }
}