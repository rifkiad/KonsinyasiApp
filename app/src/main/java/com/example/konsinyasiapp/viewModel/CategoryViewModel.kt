package com.example.konsinyasiapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.konsinyasiapp.database.MyDatabase
import com.example.konsinyasiapp.entities.CategoryData
import com.example.konsinyasiapp.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val categoryDao = MyDatabase.getDatabase(application).categoryDao()
    private val repository: CategoryRepository = CategoryRepository(categoryDao)

    val getAllCategory: LiveData<List<CategoryData>> = repository.getAllCategory

    fun insertData(categoryData: CategoryData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(categoryData)
        }
    }

    fun deleteItem(categoryData: CategoryData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(categoryData)
        }
    }
}