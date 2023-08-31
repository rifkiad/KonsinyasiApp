package com.example.konsinyasiapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val checkDatabaseEmptyLiveData = MutableLiveData<Boolean>()

    fun checkDatabaseEmpty(data: List<CategoryData>) {
        checkDatabaseEmptyLiveData.value = data.isEmpty()
    }
    fun checkDatabaseEmptyLiveData(): LiveData<Boolean> = checkDatabaseEmptyLiveData

    fun insertData(categoryData: CategoryData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(categoryData)
        }
    }

    suspend fun getCategoryIdByName(categoryData: String): Int? {
        return repository.getCategoryIdByName(categoryData)
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

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}