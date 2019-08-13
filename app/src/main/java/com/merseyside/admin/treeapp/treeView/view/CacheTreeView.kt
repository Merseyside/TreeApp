package com.merseyside.admin.treeapp.treeView.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import com.merseyside.admin.treeapp.treeView.model.Node
import com.merseyside.admin.treeapp.treeView.model.Tree

class CacheTreeView<T>(context: Context, attributeSet: AttributeSet): TreeView<T>(context, attributeSet) {

    init {
        tree = Tree()
    }

    fun addValue(value: T, node: Node<T>) {
        tree.addValue(value, node)

        setTree()
    }

    fun addNodes(nodes: List<Node<T>>) {
        nodes.forEach {node ->
            tree.addNode(node)
        }

        setTree()
    }

    fun deleteNodes() {
        adapter.getSelectedNodes().forEach {
            tree.deleteNode(it)
        }

        setTree()
    }

    companion object {
        private const val TAG = "CacheTreeView"
    }
}