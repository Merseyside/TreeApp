package com.merseyside.admin.treeapp.treeView.model

import Id
import Level
import java.util.*
import kotlin.collections.ArrayList

class Tree<T> {

    val count: Int
        get() {
            return nodeMap.size
        }

    private val nodeMap: MutableMap<Id, Node<T>> = HashMap()

    private var roots: MutableList<Node<T>> = ArrayList()

    private fun isExists(node: Node<T>?): Boolean {
        return nodeMap.containsKey(node?.id ?: return false)
    }

    fun addValue(value: T, parent: Node<T>? = getFirstRoot()): Node<T> {

        val parentId = parent?.id ?: 0

        val node = Node(value, parentId)

        if (!isRootExists()) {
            addRoot(node)
        } else {
            parent?.addChild(node.id) ?: getFirstRoot()!!.addChild(node.id)
        }

        addNodeToCollection(node)

        return node
    }

    fun addNode(node: Node<T>) {
        if (!isExists(node)) {
            if (!isRootExists()) {
                addRoot(node)
            } else {
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

            addNodeToCollection(node)

            checkNodeForDeletion(node)
        }
    }

    private fun checkNodeForDeletion(node: Node<T>) {
        if (node.isNotDeleted()) {
            getById(node.getParent())?.let {
                if (!it.isNotDeleted()) {
                    deleteNode(node)
                }
            }
        } else {
            deleteNode(node)
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

    private fun getById(id: Id) = nodeMap[id]

    @Throws(IllegalArgumentException::class)
    fun update(node: Node<T>) {
        addNodeToCollection(node)
        checkNodeForDeletion(node)
    }

    @Throws(IllegalArgumentException::class)
    fun update(tree: Tree<T>) {
        tree.toList().forEach { node ->
            update(node)
        }
    }

    fun clear() {
        roots.clear()
        nodeMap.clear()
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

                println(strBuilder.toString())

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

        return list
    }

    fun isEmpty() = count == 0

    private fun toList() = nodeMap.values.toMutableList()

    companion object {
        private const val TAG = "Tree"
    }
}


