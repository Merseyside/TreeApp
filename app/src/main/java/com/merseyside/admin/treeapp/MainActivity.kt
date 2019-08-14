package com.merseyside.admin.treeapp

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.merseyside.admin.treeapp.treeView.StorageCacheView
import com.merseyside.admin.treeapp.treeView.model.Tree

class MainActivity : AppCompatActivity() {

    private lateinit var storageCacheView: StorageCacheView<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        super.onCreate(savedInstanceState)

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
        var subTopic = tree.addValue("Subtopic 2.3", topicTwo)

        tree.addValue("subTopic 2.3.1", subTopic)
        tree.addValue("subTopic 2.3.2", subTopic)

        tree.addValue("Topic 3")
        subTopic = tree.addValue("Subtopic 3.1", topicTwo)

        tree.addValue("subTopic 3.1.1", subTopic)
        val subSubTopic = tree.addValue("subTopic 3.1.2", subTopic)

        tree.addValue("subTopic 3.1.2.1", subSubTopic)
        tree.addValue("subTopic 3.1.2.2", subSubTopic)
        tree.addValue("subTopic 3.1.2.3", subSubTopic)

        tree.printTree()

        return tree
    }
}
