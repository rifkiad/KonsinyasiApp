package com.example.konsinyasiapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.konsinyasiapp.database.MyDatabase
import com.example.konsinyasiapp.entities.DepositData
import com.example.konsinyasiapp.entities.DepositWithShop
import com.example.konsinyasiapp.entities.ProductInDeposit
import com.example.konsinyasiapp.entities.ProductInDepositWithProduct
import com.example.konsinyasiapp.entities.StatusDeposit
import com.example.konsinyasiapp.repository.DepositRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DepositViewModel(application: Application) : AndroidViewModel(application) {

    private val depositDao = MyDatabase.getDatabase(application).depositDao()
    private val repository: DepositRepository = DepositRepository(depositDao)
    private val filteredDepositLiveData = MutableLiveData<List<ProductInDepositWithProduct>>()
    private val totalAmountLiveData = MutableLiveData<Int>()
    private val checkDatabaseEmptyLiveData = MutableLiveData<Boolean>()
    private val updateStatusDeposit = MutableLiveData<StatusDeposit>()

    fun getAllFilterdDepositLiveData(): LiveData<List<ProductInDepositWithProduct>> = filteredDepositLiveData

    fun calculateTotalAmountLiveData(): LiveData<Int> = totalAmountLiveData


    val getAllDeposit: LiveData<List<DepositData>> = repository.getAllDeposit

    fun getAllDepositHome() = repository.getAllDepositHome()

    private val _idDeposit = MutableLiveData<Long>()
    val idDeposit: LiveData<Long> = _idDeposit

    suspend fun finishDeposit(depositData: DepositData) {
        repository.updateDepositFinishDate(depositData.id, depositData.depositFinish)
    }


    fun checkDatabaseEmpty(data: List<DepositWithShop>) {
        checkDatabaseEmptyLiveData.value = data.isEmpty()
    }

    fun checkDatabaseEmptyLiveData(): LiveData<Boolean> = checkDatabaseEmptyLiveData

    fun getAllShopWithDeposit(): LiveData<List<DepositWithShop>> {
        return repository.getAllShopWithDeposit()
    }

    fun getAllDepositProduct() = repository.getAllDepositProduct()

    fun insertData(depositData: DepositData) {
        viewModelScope.launch(Dispatchers.IO) {
            _idDeposit.postValue(repository.insertData(depositData))
        }
    }

    fun updateData(productInDepositData: ProductInDeposit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(productInDepositData)
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

    fun getAllUnfinishedDeposit() = repository.getAllUnfinishedDeposit()

    fun getProductDepositsForCurrentMonth(listDeposit: List<DepositData>, listProductInDeposit: List<ProductInDepositWithProduct>) {
        viewModelScope.launch {
            val filteredDeposits = repository.getProductDepositsForCurrentMonth(listDeposit, listProductInDeposit)
            filteredDepositLiveData.postValue(filteredDeposits)
        }
    }

    fun getAllFilteredInDeposit(listDeposit: List<DepositData>, listProductDeposit: List<ProductInDepositWithProduct>) {
        viewModelScope.launch {
            filteredDepositLiveData.postValue(repository.getProductDepositsForCurrentMonth(listDeposit, listProductDeposit))
        }
    }

    fun calculateTotalAmountLiveData(listProductDeposit: List<ProductInDepositWithProduct>) {
        viewModelScope.launch {
            val totalAmount = repository.calculateTotalProductSoldWithPrice(listProductDeposit)
            totalAmountLiveData.postValue(totalAmount)
        }
    }

    fun getUpdateStatusDeposit(data: StatusDeposit) {
        updateStatusDeposit.value = data
    }

    fun getUpdateStatusDepositLiveData(): LiveData<StatusDeposit> = updateStatusDeposit

    fun updateDepositStatus(id: Long, newStatus: StatusDeposit) {
        viewModelScope.launch {
            repository.updateDepositStatus(id, newStatus)
        }
    }
}