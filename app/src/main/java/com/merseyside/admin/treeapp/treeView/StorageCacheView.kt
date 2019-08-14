package com.merseyside.admin.treeapp.treeView

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.merseyside.admin.treeapp.R
import com.merseyside.admin.treeapp.treeView.model.Tree
import com.merseyside.admin.treeapp.treeView.view.CacheTreeView
import com.merseyside.admin.treeapp.treeView.view.DbTreeView
import com.merseyside.admin.treeapp.treeView.view.EditDialog
import com.merseyside.admin.treeapp.treeView.view.TreeView
import kotlinx.android.synthetic.main.view_tree.view.*
import kotlinx.android.synthetic.main.view_tree_cache.view.*

class StorageCacheView<T>(
        context: Context,
        attributeSet: AttributeSet
) : LinearLayout(context, attributeSet), View.OnClickListener {

    private lateinit var cacheView: CacheTreeView<T>
    private lateinit var storageView: DbTreeView<T>

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

        add.setOnClickListener(this)
        remove.setOnClickListener(this)
        to_cache.setOnClickListener(this)
        clear.setOnClickListener(this)
        apply.setOnClickListener(this)
        edit.setOnClickListener(this)

    }

    fun setData(tree: Tree<T>) {
        storageView.setData(tree)
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.add -> {
                    val nodes = cacheView.getSelectedNodes()

                    if (nodes.size == 1) {
                        showEditDialog(
                            value = "",
                            callback = {value ->
                                cacheView.addValue(value as T, nodes.first())
                            }
                        )
                    } else {
                        Toast.makeText(context, context.getString(R.string.add_message), Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.remove -> {
                    cacheView.deleteNodes()

                }
                R.id.to_cache -> {
                    cacheView.addNodes(storageView.getSelectedNodes().map { it.copy() })
                    storageView.cleanSelection()
                }
                R.id.clear -> {
                    cacheView.clean()
                }
                R.id.apply -> {
                    if (!cacheView.tree.isEmpty()) {
                        storageView.updateData(cacheView.tree)

                        cacheView.clean()
                    }
                }
                R.id.edit -> {
                    val nodes = cacheView.getSelectedNodes()

                    if (nodes.size == 1) {
                        nodes.first().let {
                            showEditDialog(
                                    value = it.value.toString(),
                                    callback = { value ->
                                        it.value = value as T
                                        cacheView.updateNode(it)
                                    })
                        }

                    } else {
                        Toast.makeText(context, context.getString(R.string.edit_message), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun showEditDialog(callback: (value: String) -> Unit, value: String? = null) {
        val dialog = EditDialog()

        if (value != null) {
            val bundle = Bundle()
            bundle.putString(EditDialog.VALUE_KEY, value)

            dialog.arguments = bundle
        }

        dialog.show((context as AppCompatActivity).supportFragmentManager, "edit_tag")

        dialog.setOnSaveListener(callback)
    }
}