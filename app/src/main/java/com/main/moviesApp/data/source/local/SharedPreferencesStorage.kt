package com.main.moviesApp.data.source.local

import android.content.SharedPreferences

class SharedPreferencesStorage(private val preferences: SharedPreferences) :
    KeyValueStorage {

    override fun save(key: String, value: Any) {
        when (value) {
            is String -> preferences.edit().putString(key, value).apply()
            is Long -> preferences.edit().putLong(key, value).apply()
            is Boolean -> preferences.edit().putBoolean(key, value).apply()
            is Int -> preferences.edit().putInt(key, value).apply()
        }
    }

    override fun read(key: String, defaultValue: Boolean): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }

    override fun read(key: String, defaultValue: Long): Long {
        return preferences.getLong(key, defaultValue)
    }

    override fun read(key: String, defaultValue: Int): Int {
        return preferences.getInt(key, defaultValue)
    }

    override fun read(key: String, defaultValue: String): String {
        return preferences.getString(key, defaultValue) ?: defaultValue  //because in java defaultValue can be null...
    }
}

interface KeyValueStorage {
    fun save(key: String, value: Any)
    fun read(key: String, defaultValue: Boolean): Boolean
    fun read(key: String, defaultValue: Long): Long
    fun read(key: String, defaultValue: String): String
    fun read(key: String, defaultValue: Int): Int
}