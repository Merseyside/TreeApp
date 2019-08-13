package com.merseyside.admin.treeapp.treeView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import com.merseyside.admin.treeapp.R
import com.merseyside.admin.treeapp.treeView.model.Tree
import com.merseyside.admin.treeapp.treeView.view.CacheTreeView
import com.merseyside.admin.treeapp.treeView.view.DbTreeView
import com.merseyside.admin.treeapp.treeView.view.TreeView
import kotlinx.android.synthetic.main.view_tree_cache.view.*

class StorageCacheView<T>(
        context: Context,
        attributeSet: AttributeSet
) : LinearLayout(context, attributeSet), View.OnClickListener {

    private lateinit var cacheView: CacheTreeView<T>
    private lateinit var storageView: DbTreeView<T>

    private lateinit var addButton: ImageButton
    private lateinit var removeButton: ImageButton
    private lateinit var toCacheButton: ImageButton
    private lateinit var clearButton: ImageButton
    private lateinit var applyButton: ImageButton

    init {
        loadAttrs()
        doLayout()
    }

    private fun loadAttrs() {

    }

    private fun doLayout() {
        LayoutInflater.from(context).inflate(R.layout.view_tree_cache, this)

        cacheView = findViewById(R.id.cache)
        storageView = findViewById(R.id.storage)

        addButton = findViewById(R.id.add)
        addButton.setOnClickListener(this)

        removeButton = findViewById(R.id.remove)
        removeButton.setOnClickListener(this)

        toCacheButton = findViewById(R.id.to_cache_button)
        toCacheButton.setOnClickListener(this)

        clearButton = findViewById(R.id.clear)
        clearButton.setOnClickListener(this)

        applyButton = findViewById(R.id.apply)
        applyButton.setOnClickListener(this)

    }

    fun setData(tree: Tree<T>) {
        storageView.setData(tree)
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.add -> {

                }
                R.id.remove -> {
                    cacheView.deleteNodes()

                }
                R.id.to_cache_button -> {
                    cacheView.addNodes(storageView.getSelectedNodes().map { it.copy() })
                    storageView.cleanSelection()
                }
                R.id.clear -> {
                    cacheView.clean()
                }
                R.id.apply -> {

                }
            }

        }
    }
}