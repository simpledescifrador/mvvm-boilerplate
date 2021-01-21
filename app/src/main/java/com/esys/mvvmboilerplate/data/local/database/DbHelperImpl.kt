package com.esys.mvvmboilerplate.data.local.database

import android.content.Context

class DbHelperImpl(private val context: Context) : DbHelper {
    private val appDatabase: AppDatabase by lazy {
        AppDatabase.getInstance(context)
    }
}
