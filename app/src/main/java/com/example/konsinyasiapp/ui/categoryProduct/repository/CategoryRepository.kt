package com.example.konsinyasiapp.ui.categoryProduct.repository

import androidx.lifecycle.LiveData
import com.example.konsinyasiapp.ui.categoryProduct.database.dao.CategoryDao
import com.example.konsinyasiapp.ui.product.database.entities.CategoryData

class CategoryRepository(private val categoryDao: CategoryDao) {

    val getAllProduct: LiveData<List<CategoryData>> = categoryDao.getAllProduct()

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