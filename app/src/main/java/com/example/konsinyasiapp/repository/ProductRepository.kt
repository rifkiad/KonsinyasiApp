package com.example.konsinyasiapp.repository

import androidx.lifecycle.LiveData
import com.example.konsinyasiapp.dao.ProductDao
import com.example.konsinyasiapp.entities.ProductData

class ProductRepository(private val productDao: ProductDao) {

    fun getAllProduct(idKategoriProduk: Int): LiveData<List<ProductData>> {
        return productDao.getAllProduct(idKategoriProduk)
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