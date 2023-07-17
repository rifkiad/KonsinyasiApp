package com.example.konsinyasiapp.ui.product.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.konsinyasiapp.ui.categoryProduct.database.entities.CategoryData
import com.example.konsinyasiapp.ui.categoryProduct.repository.CategoryRepository
import com.example.konsinyasiapp.ui.product.ProductRepository
import com.example.konsinyasiapp.ui.product.database.ProductDatabase
import com.example.konsinyasiapp.ui.product.database.entities.ProductData
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

    fun deleteItem(productData: ProductData) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.deleteItem(productData)
        }
    }

}