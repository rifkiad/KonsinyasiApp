package com.example.konsinyasiapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.konsinyasiapp.dao.ProductDao
import com.example.konsinyasiapp.entities.ProductData
import com.example.konsinyasiapp.entities.ProductWithCategory
import com.example.konsinyasiapp.entities.ShopData

class ProductRepository(private val productDao: ProductDao) {

    val getAllProduct: LiveData<List<ProductData>> = productDao.getAllProduct()

    fun getAllProductsWithCategories(): LiveData<List<ProductWithCategory>> {
        return productDao.getProductsWithCategories()
    }


    suspend fun insertData(productData: ProductData) {
        productDao.insertData(productData)
    }

    suspend fun updateData(productData: ProductData) {
        productDao.updateData(productData)
    }

    suspend fun deleteItem(productData: ProductData) {
        productDao.deleteItem(productData)
    }

    suspend fun deleteAll() {
        productDao.deleteAll()
    }
}