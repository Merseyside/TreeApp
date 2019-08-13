package com.merseyside.admin.treeapp.treeView.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import com.merseyside.admin.treeapp.treeView.model.Tree
import java.lang.IllegalStateException

class DbTreeView<T>(context: Context, attributeSet: AttributeSet): TreeView<T>(context, attributeSet) {

    fun setData(tree: Tree<T>) {

        this.tree = tree

        setTree()
    }

    @Throws(IllegalStateException::class)
    fun updateData(tree: Tree<T>) {
        if (tree.isEmpty()) {
            throw IllegalStateException("Nothing to update")
        } else {
            this.tree.update(tree)
        }

        setTree()
    }

    companion object {
        private const val TAG = "DbTreeView"
    }
}