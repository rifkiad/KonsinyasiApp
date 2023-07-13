package com.example.konsinyasiapp.ui.categoryProduct.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.konsinyasiapp.ui.categoryProduct.database.CategoryDatabase
import com.example.konsinyasiapp.ui.product.database.entities.CategoryData
import com.example.konsinyasiapp.ui.categoryProduct.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val categoryDao = CategoryDatabase.getDatabase(application).categoryDao()
    private val repository: CategoryRepository = CategoryRepository(categoryDao)

    val getAllProduct: LiveData<List<CategoryData>> = repository.getAllProduct

    fun insertData(categoryData: CategoryData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(categoryData)
        }
    }

    fun updateData(categoryData: CategoryData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(categoryData)
        }
    }

    fun deleteItem(categoryData: CategoryData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(categoryData)
        }
    }
}