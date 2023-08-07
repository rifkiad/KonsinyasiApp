package com.example.konsinyasiapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.konsinyasiapp.database.MyDatabase
import com.example.konsinyasiapp.entities.DepositData
import com.example.konsinyasiapp.entities.DepositWithProduct
import com.example.konsinyasiapp.entities.DepositWithShop
import com.example.konsinyasiapp.entities.ShopData
import com.example.konsinyasiapp.repository.DepositRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DepositViewModel(application: Application) : AndroidViewModel(application) {

    private val depositDao = MyDatabase.getDatabase(application).depositDao()
    private val repository: DepositRepository = DepositRepository(depositDao)

    val gettAllDeposit: LiveData<List<DepositData>> = repository.getAllDeposit

    private val _idDeposit = MutableLiveData<Long>()
    val idDeposit: LiveData<Long> = _idDeposit

    private val checkDatabaseEmptyLiveData = MutableLiveData<Boolean>()

    fun checkDatabaseEmpty(data: List<DepositWithShop>) {
        checkDatabaseEmptyLiveData.value = data.isEmpty()
    }
    fun checkDatabaseEmptyLiveData(): LiveData<Boolean> = checkDatabaseEmptyLiveData

    fun getAllShopWithDeposit(): LiveData<List<DepositWithShop>> {
        return repository.getAllShopWithDeposit()
    }

    fun insertData(depositData: DepositData) {
        viewModelScope.launch(Dispatchers.IO) {
            _idDeposit.postValue(repository.insertData(depositData))
        }
    }

    fun updateData(depositData: DepositData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(depositData)
        }
    }

    fun deleteItem(depositData: DepositData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(depositData)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}