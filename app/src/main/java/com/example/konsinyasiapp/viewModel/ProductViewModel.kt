package com.example.konsinyasiapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.konsinyasiapp.database.MyDatabase
import com.example.konsinyasiapp.entities.ProductData
import com.example.konsinyasiapp.entities.ProductWithCategory
import com.example.konsinyasiapp.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val productDao = MyDatabase.getDatabase(application).productDao()
    private val repository: ProductRepository = ProductRepository(productDao)

    val getAllProduct: LiveData<List<ProductData>> = repository.getAllProduct

    private val checkDatabaseEmptyLiveData = MutableLiveData<Boolean>()

    fun checkDatabaseEmpty(data: List<ProductWithCategory>) {
        checkDatabaseEmptyLiveData.value = data.isEmpty()
    }

    fun checkDatabaseEmptyLiveData(): LiveData<Boolean> = checkDatabaseEmptyLiveData

    fun getAllProductsWithCategories(): LiveData<List<ProductWithCategory>> {
        return repository.getAllProductsWithCategories()
    }

    fun insertData(productData: ProductData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(productData)
        }
    }

    fun updateData(productData: ProductData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(productData)
        }
    }

    fun deleteItem(productData: ProductData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(productData)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

}