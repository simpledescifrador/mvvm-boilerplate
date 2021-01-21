package com.esys.mvvmboilerplate.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.esys.mvvmboilerplate.data.api.ApiHelper
import com.esys.mvvmboilerplate.data.local.database.DbHelper
import com.esys.mvvmboilerplate.data.local.preference.PreferenceHelper
import com.esys.mvvmboilerplate.data.repository.AuthRepository
import com.esys.mvvmboilerplate.modules.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val apiHelper: ApiHelper,
    private val preferenceHelper: PreferenceHelper,
    private val dbHelper: DbHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(
                    AuthRepository(apiHelper, preferenceHelper, dbHelper)
                ) as T
            }
            else -> throw IllegalArgumentException("Unknown class name")
        }
    }
}
