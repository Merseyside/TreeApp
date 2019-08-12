package com.merseyside.admin.treeapp.treeView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.merseyside.admin.treeapp.R
import com.merseyside.admin.treeapp.treeView.adapter.TreeAdapter
import com.merseyside.admin.treeapp.treeView.model.Tree

class TreeView(context: Context, attributeSet: AttributeSet) : LinearLayout(context, attributeSet) {

    private val adapter: TreeAdapter

    init {
        loadAttrs()

        adapter = TreeAdapter()

        doLayout()
    }

    private fun loadAttrs() {

    }

    private fun doLayout() {
        LayoutInflater.from(context).inflate(R.layout.view_tree, this)

        findViewById<RecyclerView>(R.id.tree).adapter = adapter
    }

    fun setData(tree: Tree<String>) {
        adapter.add(tree.toList())
    }


}