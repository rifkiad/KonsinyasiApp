package com.example.konsinyasiapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.konsinyasiapp.database.MyDatabase
import com.example.konsinyasiapp.entities.ShopData
import com.example.konsinyasiapp.repository.ShopRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ShopViewModel(application: Application) : AndroidViewModel(application) {

    private val shopDao = MyDatabase.getDatabase(application).shopDao()
    private val repository: ShopRepository = ShopRepository(shopDao)

    val getAllData: LiveData<List<ShopData>> = repository.getAllData

    fun insertData(shopData: ShopData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(shopData)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<ShopData>> {
        return repository.searchDatabase(searchQuery)
    }
}