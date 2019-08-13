package com.merseyside.admin.treeapp.treeView.view

import android.app.Dialog
import android.os.Bundle
import android.view.Window.FEATURE_NO_TITLE
import com.merseyside.admin.treeapp.R
import com.upstream.basemvvmimpl.presentation.dialog.BaseDialog
import kotlinx.android.synthetic.main.dialog_edit.*

class EditDialog : BaseDialog() {

    private var onSaveFunc: (value: String) -> Unit? = {}

    fun setOnSaveListener(func: (value: String) -> Unit) {
        this.onSaveFunc = func
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(context!!, R.style.ThemeOverlay_AppCompat_Dialog)

        dialog.requestWindowFeature(FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_edit)

        dialog.window!!.decorView.setBackgroundResource(android.R.color.transparent)

        if (arguments != null && arguments!!.containsKey(VALUE_KEY)) {
            dialog.value.setText(arguments!!.getString(VALUE_KEY))
        }

        dialog.save.setOnClickListener {

            val text = dialog.value.text.toString()
            if(text.isNotEmpty()) {
                onSaveFunc(text)

                dismiss()
            }
        }

        return dialog
    }

    override fun performInjection() {}

    companion object {
        const val VALUE_KEY = "value"
    }
}