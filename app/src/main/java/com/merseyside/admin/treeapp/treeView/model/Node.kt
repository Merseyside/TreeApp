package com.merseyside.admin.treeapp.treeView.model

import Id

data class Node<T>(
        val id: Id,
        var value: T,
        private val parent: Id,
        private var childList: MutableList<Id> = ArrayList(),
        private var isDeleted: Boolean = false
) {

    internal fun addChild(id: Id) {
        childList.add(id)
    }

    internal fun getChildren() = childList

    internal fun delete() {
        isDeleted = true
    }

    internal fun getParent(): Id {
        return parent
    }

    fun isNotDeleted() = !isDeleted

    fun copy(): Node<T> {
        return Node(
                id = id,
                value = value,
                parent = parent,
                childList = getChildren(),
                isDeleted = isDeleted
        )
    }
}