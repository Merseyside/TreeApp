package com.merseyside.admin.treeapp.treeView

import Level
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.merseyside.admin.treeapp.R
import com.merseyside.admin.treeapp.treeView.model.Node
import com.merseyside.admin.treeapp.treeView.model.Tree

class StorageCacheView(context: Context, attributeSet: AttributeSet) : LinearLayout(context, attributeSet) {

    private lateinit var cacheView: TreeView
    private lateinit var storageView: TreeView

    init {
        loadAttrs()
        doLayout()
    }

    private fun loadAttrs() {

    }

    private fun doLayout() {
        LayoutInflater.from(context).inflate(R.layout.view_tree_cache, this)

        cacheView = findViewById(R.id.cache)
        storageView = findViewById(R.id.storage)

    }

    fun setData(tree: Tree<String>) {
        storageView.setData(tree)
    }
}