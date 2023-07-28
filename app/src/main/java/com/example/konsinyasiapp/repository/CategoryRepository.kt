package com.example.konsinyasiapp.repository

import androidx.lifecycle.LiveData
import com.example.konsinyasiapp.dao.CategoryDao
import com.example.konsinyasiapp.entities.CategoryData
import com.example.konsinyasiapp.entities.ProductData

class CategoryRepository(private val categoryDao: CategoryDao) {

    val getAllCategory: LiveData<List<CategoryData>> = categoryDao.getAllCategory()

    suspend fun insertCategory(categoryData: CategoryData) {
        categoryDao.insertData(categoryData)
    }

    suspend fun getCategoryIdByName(categoryData: String): Int? {
        return categoryDao.getCategoryIdByName(categoryData)
    }

    suspend fun insertData(categoryData: CategoryData) {
        categoryDao.insertData(categoryData)
    }

    suspend fun updateData(categoryData: CategoryData) {
        categoryDao.updateData(categoryData)
    }

    suspend fun deleteItem(categoryData: CategoryData) {
        categoryDao.deleteItem(categoryData)
    }

    suspend fun deleteAll() {
        categoryDao.deleteAll()
    }
}