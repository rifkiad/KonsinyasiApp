package com.example.konsinyasiapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.konsinyasiapp.database.MyDatabase
import com.example.konsinyasiapp.entities.ProductInDeposit
import com.example.konsinyasiapp.repository.ProductInDepositRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductInDepositViewModel(application: Application) : AndroidViewModel(application) {

    private val productInDepositDao = MyDatabase.getDatabase(application).productInDepositDao()
    private val repository: ProductInDepositRepository =
        ProductInDepositRepository(productInDepositDao)

    private val _totalSoldProductLiveData = MutableLiveData<Long>()
    val totalSoldProductLiveData: LiveData<Long>
        get() = _totalSoldProductLiveData


    fun filterProduct(idDeposit: Long) = repository.filterProduct(idDeposit)

    fun insertDataProductInDeposit(productInDeposit: ProductInDeposit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(productInDeposit)
        }
    }

    fun updateTotalSoldProduct(idDeposit: Long) {
        viewModelScope.launch(Dispatchers.Main) {
            val totalSoldProduct = repository.getTotalSoldProduct(idDeposit)
            _totalSoldProductLiveData.value = totalSoldProduct
        }
    }
}
