package com.example.konsinyasiapp.setting

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow


private val Context.prefDataStore by preferencesDataStore("settings")

class SettingPrefereces constructor(context: Context) {

    private val settingDataStore = context.prefDataStore
    private val themeKEY = booleanPreferencesKey("theme_settings")

    fun getThemeSetting(): Flow<Boolean> =
        settingDataStore.data.map { preferences ->
            preferences[themeKEY] ?: false
        }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        settingDataStore.edit { preferences ->
            preferences[themeKEY] = isDarkModeActive
        }
    }
}