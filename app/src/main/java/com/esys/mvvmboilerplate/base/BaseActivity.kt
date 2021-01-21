package com.esys.mvvmboilerplate.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.esys.mvvmboilerplate.utils.LoadingDialog

abstract class BaseActivity : AppCompatActivity() {

    lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog = LoadingDialog(this)
        setupViewModel()
        initViews()
    }

    override fun onStart() {
        observeViewModel()
        setupEvents()
        super.onStart()
    }

    protected abstract fun initViews()
    protected abstract fun setupEvents()
    protected abstract fun setupViewModel()
    protected abstract fun observeViewModel()
}