package com.example.konsinyasiapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.konsinyasiapp.database.MyDatabase
import com.example.konsinyasiapp.entities.DepositWithProduct
import com.example.konsinyasiapp.entities.ProductInDeposit
import com.example.konsinyasiapp.repository.ProductInDepositRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductInDepositViewModel(application: Application) : AndroidViewModel(application) {

    private val productInDepositDao = MyDatabase.getDatabase(application).productInDepositDao()
    private val repository: ProductInDepositRepository =
        ProductInDepositRepository(productInDepositDao)

    val gettAllDeposit: LiveData<List<ProductInDeposit>> = repository.getAllDeposit

    fun getAllProductWithDeposit(): LiveData<List<DepositWithProduct>> {
        return repository.getAllProductWithDeposit()
    }

    fun insertData(productInDeposit: ProductInDeposit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(productInDeposit)
        }
    }

    fun updateData(productInDeposit: ProductInDeposit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(productInDeposit)
        }
    }

    fun deleteItem(productInDeposit: ProductInDeposit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(productInDeposit)
        }
    }
}