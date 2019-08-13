package com.merseyside.admin.treeapp

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.merseyside.admin.treeapp.treeView.StorageCacheView
import com.merseyside.admin.treeapp.treeView.model.Tree

class MainActivity : AppCompatActivity() {

    private lateinit var storageCacheView: StorageCacheView<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        setContentView(R.layout.activity_main)

        storageCacheView = findViewById(R.id.storageCacheView)

        val tree = generateData()

        storageCacheView.setData(tree)
    }

    private fun generateData(): Tree<String> {
        val tree = Tree<String>()

        tree.addValue("Content")
        tree.addValue("Topic 1")
        val topicTwo = tree.addValue("Topic 2")
        tree.addValue("Subtopic 2.1", topicTwo)
        tree.addValue("Subtopic 2.2", topicTwo)
        val subTopicTwoThree = tree.addValue("Subtopic 2.3", topicTwo)

        tree.addValue("subTopic 2.3.1", subTopicTwoThree)
        tree.addValue("subTopic 2.3.2", subTopicTwoThree)

        tree.addValue("Topic 3")

        tree.printTree()

        return tree
    }
}
