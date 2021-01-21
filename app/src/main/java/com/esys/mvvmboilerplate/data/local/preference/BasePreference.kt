package com.esys.mvvmboilerplate.data.local.preference

interface BasePreference {
    fun checkIfContainsValue(key: String): Boolean

    fun clearPrefs()

    fun getBooleanFromPrefs(key: String): Boolean

    fun getIntFromPrefs(key: String): Int

    fun getLongFromPrefs(key: String): Long

    fun getStringFromPrefs(key: String): String?

    fun getStringFromPrefs(key: String, defaultValue: String): String?

    fun setBooleanToPrefs(key: String, value: Boolean)

    fun setIntToPrefs(key: String, value: Int)

    fun setLongToPrefs(key: String, value: Long)

    fun setStringToPrefs(key: String, value: String)
}
