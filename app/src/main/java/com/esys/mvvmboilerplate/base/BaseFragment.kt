package com.esys.mvvmboilerplate.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.esys.mvvmboilerplate.utils.LoadingDialog

abstract class BaseFragment : Fragment() {
    lateinit var loadingDialog: LoadingDialog

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadingDialog = LoadingDialog(context)
        setupViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        observeViewModel()
        setupEvents()
        super.onViewCreated(view, savedInstanceState)
    }

    protected abstract fun initViews()
    protected abstract fun setupEvents()
    protected abstract fun setupViewModel()
    protected abstract fun observeViewModel()
}