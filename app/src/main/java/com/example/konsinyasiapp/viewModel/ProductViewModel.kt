package com.example.konsinyasiapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.konsinyasiapp.repository.CategoryRepository
import com.example.konsinyasiapp.repository.ProductRepository
import com.example.konsinyasiapp.database.ProductDatabase
import com.example.konsinyasiapp.entities.ProductData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val productDao = ProductDatabase.getDatabase(application).productDao()
    private val categoryDao = ProductDatabase.getDatabase(application).categoryDao()

    private val productRepository: ProductRepository = ProductRepository(productDao)
    private val categoryRepository: CategoryRepository = CategoryRepository(categoryDao)

    val getAllProduct: LiveData<List<ProductData>> = productRepository.getAllProduct(0)

    fun insertData(productData: ProductData) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.insertData(productData)
        }
    }

    fun updateData(productData: ProductData) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.updateData(productData)
        }
    }

    fun deleteItem(productData: ProductData) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.deleteItem(productData)
        }
    }

}