package com.esys.mvvmboilerplate.modules.home

import androidx.lifecycle.viewModelScope
import com.esys.mvvmboilerplate.base.BaseViewModel
import com.esys.mvvmboilerplate.data.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(private val authRepository: AuthRepository) : BaseViewModel() {
    suspend fun tryShowLoading() {
        viewModelScope.launch {
            loading.postValue(true)
            delay(3_000)
            loading.postValue(false)
        }
    }
}