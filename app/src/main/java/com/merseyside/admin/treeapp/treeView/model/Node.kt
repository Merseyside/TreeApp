package com.merseyside.admin.treeapp.treeView.model

import Id

data class Node<T>(
        var value: T,
        private val parent: Id,
        private var childList: MutableList<Id> = ArrayList(),
        private var isDeleted: Boolean = false
) {

    var id: Id = hashCode()
    private set

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
                value = value,
                parent = parent,
                childList = getChildren(),
                isDeleted = isDeleted
        ).also { it.id = id }
    }

    override fun hashCode(): Int {
        var result = value?.hashCode() ?: 0
        result = 31 * result + parent
        result = 31 * result + childList.hashCode()
        result = 31 * result + isDeleted.hashCode()
        result = 31 * result + id
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node<*>

        if (value != other.value) return false
        if (parent != other.parent) return false
        if (childList != other.childList) return false
        if (isDeleted != other.isDeleted) return false
        if (id != other.id) return false

        return true
    }
}