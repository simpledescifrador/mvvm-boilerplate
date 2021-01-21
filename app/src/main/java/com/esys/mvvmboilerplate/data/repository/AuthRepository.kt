package com.esys.mvvmboilerplate.data.repository

import com.esys.mvvmboilerplate.data.api.ApiHelper
import com.esys.mvvmboilerplate.data.local.database.DbHelper
import com.esys.mvvmboilerplate.data.local.preference.PreferenceHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AuthRepository(
    private val apiHelper: ApiHelper,
    private val preferenceHelper: PreferenceHelper,
    private val dbHelper: DbHelper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val retryTimes: Int = 3
) : BaseRepository() {
}
