package com.merseyside.admin.treeapp.treeView.adapter

import Level
import com.merseyside.admin.treeapp.R
import com.merseyside.admin.treeapp.treeView.model.Node
import com.merseyside.admin.treeapp.treeView.model.NodeItemViewModel
import com.upstream.basemvvmimpl.presentation.adapter.BaseAdapter

class TreeAdapter : BaseAdapter<Pair<Node<String>, Level>, NodeItemViewModel>() {

    override fun createItemViewModel(obj: Pair<Node<String>, Level>): NodeItemViewModel {
        return NodeItemViewModel(obj)
    }

    override fun getBindingVariable(): Int {
        return BR.obj
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.view_tree
    }

    override fun setFilter(query: String) {}
}