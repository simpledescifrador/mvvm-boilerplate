package com.esys.mvvmboilerplate.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import com.esys.mvvmboilerplate.R


@SuppressLint("InflateParams")
class LoadingDialog(context: Context) {
    private var dialog: AlertDialog? = null

    init {
        val builder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.CustomDialogTheme))
        val inflater: LayoutInflater = (context as Activity).layoutInflater
        builder.setView(inflater.inflate(R.layout.dialog_loading, null))
        builder.setCancelable(false)
        dialog = builder.create()
    }

    fun show() {
        dialog?.show()
    }

    fun dismiss() {
        dialog?.dismiss()
    }
}
