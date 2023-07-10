package com.example.konsinyasiapp.ui.shop

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.konsinyasiapp.database.ShopData
import com.example.konsinyasiapp.database.ShopDatabase
import com.example.konsinyasiapp.repository.ShopRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ShopViewModel(application: Application) : AndroidViewModel(application) {

    private val shopDao = ShopDatabase.getDatabase(application).shopDao()
    private val repository: ShopRepository = ShopRepository(shopDao)

    val getAllData: LiveData<List<ShopData>> = repository.getAllData

    private val _text = MutableLiveData<String>().apply {
        value = "This is shop Fragment"
    }
    val text: LiveData<String> = _text

    fun insertData(shopData: ShopData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(shopData)
        }
    }

    fun updateData(shopData: ShopData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(shopData)
        }
    }

    fun deleteItem(shopData: ShopData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(shopData)
        }
    }

}