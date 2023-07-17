package com.example.konsinyasiapp.ui.product

import androidx.lifecycle.LiveData
import com.example.konsinyasiapp.ui.product.database.dao.ProductDao
import com.example.konsinyasiapp.ui.product.database.entities.ProductData

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