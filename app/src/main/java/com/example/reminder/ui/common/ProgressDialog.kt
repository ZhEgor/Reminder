package com.example.reminder.ui.common

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.example.reminder.R

class ProgressDialog {

    private var dialog: Dialog? = null

    fun showProgressBar(context: Context) {
        if (dialog != null) return

        dialog = Dialog(context).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog_progress)
            setCancelable(false)
            window?.setBackgroundDrawableResource(R.color.white_0)
            show()
        }
    }

    fun hideProgressBar() {
        dialog?.let {
            it.dismiss()
            dialog = null
        }
    }
}