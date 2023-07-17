package com.example.konsinyasiapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.konsinyasiapp.setting.SettingPrefereces

class HomeViewModel(private val preferences: SettingPrefereces) : ViewModel() {

    fun getTheme() = preferences.getThemeSetting().asLiveData()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    class Factory(private val preferences: SettingPrefereces) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            HomeViewModel(preferences) as T
    }
}