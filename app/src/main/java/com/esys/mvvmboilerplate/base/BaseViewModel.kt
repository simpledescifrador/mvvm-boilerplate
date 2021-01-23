package com.esys.mvvmboilerplate.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    protected val loading = MutableLiveData<Boolean>()
    fun getLoading(): LiveData<Boolean> = loading
}
