package com.dimadyuk.dogs.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPrefsHelper {

    fun saveUpdateTime(time: Long) {
        prefs?.edit(commit = true) {
            putLong(PREF_TIME, time)
        }
    }

    fun getUpdateTime() = prefs?.getLong(PREF_TIME, 0)

    companion object {
        private var prefs: SharedPreferences? = null
        private const val PREF_TIME = "Pref time"

        @Volatile
        private var instance: SharedPrefsHelper? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildHelper(context).also {
                instance = it
            }
        }

        private fun buildHelper(context: Context): SharedPrefsHelper {
            prefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPrefsHelper()
        }
    }
}