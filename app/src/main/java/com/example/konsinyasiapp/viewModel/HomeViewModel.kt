package com.example.konsinyasiapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.konsinyasiapp.entities.DepositWithShop
import com.example.konsinyasiapp.setting.SettingPrefereces

class HomeViewModel(private val preferences: SettingPrefereces) : ViewModel() {

    private val checkDatabaseEmptyLiveData = MutableLiveData<Boolean>()
    fun getTheme() = preferences.getThemeSetting().asLiveData()

    class Factory(private val preferences: SettingPrefereces) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            HomeViewModel(preferences) as T
    }

    fun checkDatabaseEmpty(data: List<DepositWithShop>) {
        checkDatabaseEmptyLiveData.value = data.isEmpty()
    }
    fun checkDatabaseEmptyLiveData(): LiveData<Boolean> = checkDatabaseEmptyLiveData

}