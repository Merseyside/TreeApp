package com.merseyside.admin.treeapp.treeView.model

import Id
import Level
import android.util.Log
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList

class Tree<T> {

    private val nodeMap: MutableMap<Id, Node<T>> = HashMap()

    private var roots: MutableList<Node<T>> = ArrayList()

    private var idCounter = 0

    private fun isExists(node: Node<T>?): Boolean {
        return nodeMap.containsKey(node?.id ?: return false)
    }

    fun addValue(value: T, parent: Node<T>? = getFirstRoot()): Node<T> {

        val parentId = parent?.id ?: -1

        val node = Node(++idCounter, value, parentId)

        if (!isRootExists()) {
            addRoot(node)
        } else {
            parent?.addChild(idCounter) ?: getFirstRoot()!!.addChild(idCounter)
        }

        addNodeToCollection(node)

        return node
    }

    fun addNode(node: Node<T>) {
        if (!isExists(node)) {
            if (!isRootExists()) {
                addRoot(node)
            } else {
                addNodeToCollection(node)

                var isAlreadyRoot = false

                roots = roots
                        .asSequence()
                        .filterNot { root ->
                            node.getChildren().contains(root.id).also {
                                if (!isAlreadyRoot) {
                                    isAlreadyRoot = root.id == node.id
                                }
                            }
                        }.toMutableList()

                val isChild = nodeMap
                        .asSequence()
                        .filter { entry -> entry.value.getChildren().contains(node.id) }
                        .take(1)
                        .map { true }
                        .firstOrNull() ?: false

                if (!isChild && !isAlreadyRoot) {
                    addRoot(node)
                }
            }

            if (node.isNotDeleted()) {
                getById(node.getParent())?.let {
                    if (!it.isNotDeleted()) {

                        deleteNode(node)
                    }
                }
            }
        }
    }

    fun deleteNode(node: Node<T>?) {
        if (isExists(node)) {
            node!!.delete()

            node.getChildren().forEach {
                deleteNode(nodeMap[it])
            }
        }
    }

    private fun addNodeToCollection(node: Node<T>) {
        nodeMap[node.id] = node
    }

    private fun isRootExists(): Boolean {
        return roots.isNotEmpty()
    }

    private fun addRoot(node: Node<T>) {
        roots.add(node)
    }

    private fun getFirstRoot(): Node<T>? {
        return if (roots.isNotEmpty()) {
            roots.first()
        } else {
            null
        }
    }

    fun getById(id: Id): Node<T>? {
        return nodeMap[id]
    }

    fun getById(ids: List<Id>) = ids.mapNotNull { id -> getById(id) }

    @Throws(IllegalArgumentException::class)
    private fun update(node: Node<T>) {
        //if (isContains(node.id)) {
            nodeMap[node.id] = node
        //} else {
//            addNode
//        }
    }

    @Throws(IllegalArgumentException::class)
    fun update(tree: Tree<T>) {
        tree.toList().forEach { node ->
            update(node)
        }
    }

    private fun isContains(id: Id): Boolean {
        return nodeMap.containsKey(id)
    }

    fun clear() {
        idCounter = 0
        roots.clear()
        nodeMap.clear()
    }

    fun count(): Int {
        return nodeMap.size
    }

    fun printTree() {

        val indentStr = "  "

        fun printTree(id: Id, level: Int) {
            val node = getById(id)

            if (node != null) {
                val strBuilder = StringBuilder()

                for (i in 0 until level) {
                    strBuilder.append(indentStr)
                }

                strBuilder.append(node.value)

                System.out.println(strBuilder.toString())

                node.getChildren().forEach { nodeId ->
                    printTree(nodeId, level + 1)
                }
            }
        }

        roots.forEach { root ->
            printTree(root.id, 0)
        }
    }

    fun toListWithLevel(): List<Pair<Node<T>, Level>> {
        val list = ArrayList<Pair<Node<T>, Level>>()

        fun toListWithLevel(id: Id, level: Level) {
            val node = getById(id)

            if (node != null) {
                list.add(node to level)

                node.getChildren().forEach { nodeId ->
                    toListWithLevel(nodeId, level + 1)
                }
            }
        }

        roots.forEach { root ->
            toListWithLevel(root.id, 0)
        }

        return list.also { System.out.println("count = ${list.count()}") }
    }

    fun isEmpty() = count() == 0

    fun toList() = nodeMap.values.toMutableList()

    companion object {
        private const val TAG = "Tree"
    }
}


