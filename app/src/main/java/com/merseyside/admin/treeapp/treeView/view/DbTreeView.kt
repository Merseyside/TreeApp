package com.merseyside.admin.treeapp.treeView.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import com.merseyside.admin.treeapp.treeView.model.Node
import com.merseyside.admin.treeapp.treeView.model.Tree

class DbTreeView<T>(context: Context, attributeSet: AttributeSet): TreeView<T>(context, attributeSet) {

    fun setData(tree: Tree<T>) {

        this.tree = tree

        setTree()
    }

    @Throws(IllegalStateException::class)
    fun updateData(tree: Tree<T>): List<Node<T>> {
        if (tree.isEmpty()) {
            throw IllegalStateException("Nothing to update")
        } else {
            return this.tree.update(tree.toList()).map { it.copy() }.also {

                Log.d(TAG, "count = ${it.size}")
                setTree()
            }
        }
    }

    companion object {
        private const val TAG = "DbTreeView"
    }
}