package com.example.konsinyasiapp.repository

import androidx.lifecycle.LiveData
import com.example.konsinyasiapp.dao.CategoryDao
import com.example.konsinyasiapp.entities.CategoryData

class CategoryRepository(private val categoryDao: CategoryDao) {

    val getAllCategory: LiveData<List<CategoryData>> = categoryDao.getAllCategory()

    suspend fun insertCategory(categoryData: CategoryData) {
        categoryDao.insertData(categoryData)
    }

    suspend fun insertData(categoryData: CategoryData) {
        categoryDao.insertData(categoryData)
    }

    suspend fun deleteItem(categoryData: CategoryData) {
        categoryDao.deleteItem(categoryData)
    }

    suspend fun deleteAll() {
        categoryDao.deleteAll()
    }
}