package com.esys.mvvmboilerplate.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val loading = MutableLiveData<Boolean>()
}
