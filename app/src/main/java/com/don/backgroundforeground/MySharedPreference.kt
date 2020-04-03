package com.don.backgroundforeground

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager


object MySharedPreference {

    private const val KEY_TEXT = "key_text"

    private fun getPreference(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun setEditTextValue(context: Context, value: String) {
        val editor = getPreference(context).edit()
        editor.putString(KEY_TEXT, value)
        editor.apply()
    }

    fun getEditTextValue(context: Context): String? {
        return getPreference(context).getString(KEY_TEXT, "")
    }

    fun clearPreference(context: Context) {
        getPreference(context).edit().clear().apply()
    }


}