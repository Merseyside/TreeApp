package com.merseyside.admin.treeapp.treeView.model

import Id

data class Node<T>(val id: Id, var value: T) {

    private val childList by lazy { ArrayList<Id>() }

    internal fun addChild(id: Id) {
        childList.add(id)
    }

    fun getChildren(): List<Id> {
        return childList
    }
}