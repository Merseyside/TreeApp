package com.merseyside.admin.treeapp.treeView.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.merseyside.admin.treeapp.R
import com.merseyside.admin.treeapp.treeView.adapter.TreeAdapter
import com.merseyside.admin.treeapp.treeView.model.Node
import com.merseyside.admin.treeapp.treeView.model.Tree

abstract class TreeView<T>(context: Context, attributeSet: AttributeSet) : LinearLayout(context, attributeSet) {

    protected val adapter: TreeAdapter<T>

    lateinit var tree: Tree<T>

    init {
        loadAttrs()
        adapter = TreeAdapter()
        doLayout()
    }

    private fun loadAttrs() {}

    private fun doLayout() {
        LayoutInflater.from(context).inflate(R.layout.view_tree, this)

        findViewById<RecyclerView>(R.id.tree).adapter = adapter
    }

    fun getSelectedNodes(): List<Node<T>> {
        return adapter.getSelectedNodes()
    }

    fun clean() {
        tree.clear()
        adapter.removeAll()
    }

    fun cleanSelection() {
        adapter.cleanSelection()
    }

    protected fun setTree() {
        adapter.removeAll()
        adapter.setTree(tree)
    }
}