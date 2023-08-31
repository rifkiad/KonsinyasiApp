package com.example.konsinyasiapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.konsinyasiapp.database.MyDatabase
import com.example.konsinyasiapp.entities.DepositData
import com.example.konsinyasiapp.entities.ShopData
import com.example.konsinyasiapp.repository.ShopRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ShopViewModel(application: Application) : AndroidViewModel(application) {

    private val shopDao = MyDatabase.getDatabase(application).shopDao()
    private val repository: ShopRepository = ShopRepository(shopDao)

    val getAllData: LiveData<List<ShopData>> = repository.getAllData

    private val checkDatabaseEmptyLiveData = MutableLiveData<Boolean>()

    fun checkDatabaseEmpty(data: List<ShopData>) {
        checkDatabaseEmptyLiveData.value = data.isEmpty()
    }

    fun checkDatabaseEmptyLiveData(): LiveData<Boolean> = checkDatabaseEmptyLiveData

    fun checkDepositInShop(data: List<DepositData>) {
        checkDatabaseEmptyLiveData.value = data.isEmpty()
    }

    fun checkDepositInShopLiveData(): LiveData<Boolean> = checkDatabaseEmptyLiveData

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

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun getAllDepositInShop(shopId: Int) = repository.getAllDepositInStore(shopId)
}