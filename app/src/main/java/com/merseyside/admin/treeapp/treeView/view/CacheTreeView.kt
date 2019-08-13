package com.merseyside.admin.treeapp.treeView.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import com.merseyside.admin.treeapp.treeView.model.Node

class CacheTreeView<T>(context: Context, attributeSet: AttributeSet): TreeView<T>(context, attributeSet) {


    fun addNode(node: Node<T>) {
        tree.addNode(node)

        setTree()
    }

    fun addNodes(nodes: List<Node<T>>) {
        Log.d(TAG, "count = ${nodes.size}")

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

    private fun setTree() {
        adapter.removeAll()
        adapter.setTree(tree)
    }

    companion object {
        private const val TAG = "CacheTreeView"
    }
}