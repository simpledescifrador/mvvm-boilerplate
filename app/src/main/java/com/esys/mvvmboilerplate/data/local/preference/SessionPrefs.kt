package com.esys.mvvmboilerplate.data.local.preference

import android.content.Context
import android.content.SharedPreferences

class SessionPrefs(context: Context) : BasePreference {

    companion object {
        private const val PREF_NAME = "SESSION"
    }

    private var prefs: SharedPreferences

    init {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    override fun checkIfContainsValue(key: String): Boolean = prefs.contains(key)

    override fun clearPrefs() = prefs.edit().clear().apply()

    override fun getBooleanFromPrefs(key: String): Boolean = prefs.getBoolean(key, false)

    override fun getIntFromPrefs(key: String): Int = prefs.getInt(key, 0)

    override fun getLongFromPrefs(key: String): Long = prefs.getLong(key, 0)

    override fun getStringFromPrefs(key: String): String? = prefs.getString(key, "")

    override fun getStringFromPrefs(key: String, defaultValue: String): String? =
        prefs.getString(key, defaultValue)

    override fun setBooleanToPrefs(key: String, value: Boolean) =
        prefs.edit().putBoolean(key, value).apply()

    override fun setIntToPrefs(key: String, value: Int) =
        prefs.edit().putInt(key, value).apply()

    override fun setLongToPrefs(key: String, value: Long) =
        prefs.edit().putLong(key, value).apply()

    override fun setStringToPrefs(key: String, value: String) =
        prefs.edit().putString(key, value).apply()
}
