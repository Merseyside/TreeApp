package com.merseyside.admin.treeapp.treeView.model

import Id
import Level
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList

class Tree<T> {

    private val nodeMap: MutableMap<Id, Node<T>> = HashMap()

    var roots: MutableList<Node<T>> = ArrayList()

    private var idCounter = 0

    fun addValue(value: T, parent: Node<T>? = roots.firstOrNull()): Node<T> {
        val node = Node(++idCounter, value)

        if (!isRootExists()) {
            addRoot(node)
        } else {
            parent?.addChild(idCounter) ?: getFirstRoot().addChild(idCounter)
        }

        addNodeToCollection(node)

        return node
    }

    fun addNode(node: Node<T>) {
        if (!isRootExists()) {
            addRoot(node)
        } else {
            val isFound = nodeMap
                    .asSequence()
                    .filter { entry -> entry.value.getChildren().contains(node.id) }
                    .take(1)
                    .map { true }
                    .firstOrNull() ?: false

            roots = roots
                    .filterNot { root ->
                        node.getChildren().contains(root.id)
                    }.toMutableList()

            if (!isFound) {
                addRoot(node)
            }
        }

        addNodeToCollection(node)
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

    private fun removeRoot(node: Node<T>) {
        roots.remove(node)
    }

    private fun isNodeIdInRoots(id: Id): Boolean {
        roots.forEach { node ->
            if (node.id == id) {
                return true
            }
        }

        return false
    }

    private fun getFirstRoot(): Node<T> {
        if (roots.isNotEmpty()) {
            return roots.first()
        } else {
            throw IllegalStateException("Roots are empty")
        }
    }

    fun getById(id: Id): Node<T>? {
        return nodeMap[id]
    }

    fun getById(ids: List<Id>) = ids.mapNotNull { id -> getById(id) }

    @Throws(IllegalArgumentException::class)
    fun update(node: Node<T>) {
        if (isContains(node.id)) {
            nodeMap[node.id] = node
        } else {
            throw IllegalArgumentException("No node found with passed id")
        }
    }

    @Throws(IllegalArgumentException::class)
    fun update(map: List<Node<T>>) {
        map.forEach { node ->
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

    fun toList(): List<Pair<Node<T>, Level>> {
        val list = ArrayList<Pair<Node<T>, Level>>()

        fun toList(id: Id, level: Level) {
            val node = getById(id)

            if (node != null) {
                list.add(node to level)

                node.getChildren().forEach { nodeId ->
                    toList(nodeId, level + 1)
                }
            }
        }

        roots.forEach { root ->
            toList(root.id, 0)
        }

        return list
    }
}


