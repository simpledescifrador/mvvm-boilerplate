package com.esys.mvvmboilerplate.base

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.esys.mvvmboilerplate.data.api.ApiHelper
import com.esys.mvvmboilerplate.data.api.ApiHelperImpl
import com.esys.mvvmboilerplate.data.api.RetrofitBuilder
import com.esys.mvvmboilerplate.data.local.database.DbHelper
import com.esys.mvvmboilerplate.data.local.database.DbHelperImpl
import com.esys.mvvmboilerplate.data.local.preference.PreferenceHelper
import com.esys.mvvmboilerplate.data.local.preference.PreferenceHelperImpl
import com.esys.mvvmboilerplate.data.local.preference.SessionPrefs
import timber.log.Timber

class BaseApplication : Application() {

    val apiHelper: ApiHelper by lazy {
        ApiHelperImpl(RetrofitBuilder.apiService)
    }

    val dbHelper: DbHelper by lazy {
        DbHelperImpl(this)
    }

    val preferenceHelper: PreferenceHelper by lazy {
        PreferenceHelperImpl(SessionPrefs(this))
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}