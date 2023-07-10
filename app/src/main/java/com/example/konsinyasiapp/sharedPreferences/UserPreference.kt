package com.example.konsinyasiapp.sharedPreferences

import android.content.Context

internal class UserPreference(context: Context) {
    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val NAME = "name"
    }
    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    fun setUser(value: UserModel) {
        val editor = preferences.edit()
        editor.putString(NAME, value.name)
        editor.apply()
    }
    fun getUser(): UserModel {
        val model = UserModel()
        model.name = preferences.getString(NAME, "")

        return model
    }
}