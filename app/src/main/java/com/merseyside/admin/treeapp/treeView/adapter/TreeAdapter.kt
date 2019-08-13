package com.merseyside.admin.treeapp.treeView.adapter

import Level
import com.merseyside.admin.treeapp.BR
import com.merseyside.admin.treeapp.R
import com.merseyside.admin.treeapp.treeView.model.Node
import com.merseyside.admin.treeapp.treeView.model.NodeItemViewModel
import com.merseyside.admin.treeapp.treeView.model.Tree
import com.upstream.basemvvmimpl.presentation.adapter.BaseAdapter

class TreeAdapter<T> : BaseAdapter<Pair<Node<T>, Level>, NodeItemViewModel<T>>() {

    override fun createItemViewModel(obj: Pair<Node<T>, Level>): NodeItemViewModel<T> {
        return NodeItemViewModel(obj)
    }

    override fun getBindingVariable(): Int {
        return BR.obj
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.view_node
    }

    override fun setFilter(query: String) {}

    fun getSelectedNodes(): List<Node<T>> {
        return getSelectedNodeViewModels().map { it.getItem().first }
    }

    fun setTree(tree: Tree<T>) {
        add(tree.toListWithLevel())
    }

    fun cleanSelection() {
        (0 until itemCount).forEach {
            val item = getObjForPosition(it)

            item.isSelected = false
        }
    }

    private fun getSelectedNodeViewModels(): List<NodeItemViewModel<T>> {
        return (0 until itemCount).mapNotNull {
            val item = getObjForPosition(it)

            if (item.isSelected) item
            else null
        }
    }

    companion object {
        private const val TAG = "TreeAdapter"
    }
}