package com.example.konsinyasiapp.repository

import androidx.lifecycle.LiveData
import com.example.konsinyasiapp.dao.ProductInDepositDao
import com.example.konsinyasiapp.entities.DepositWithProduct
import com.example.konsinyasiapp.entities.ProductInDeposit

class ProductInDepositRepository(private val productInDepositDao: ProductInDepositDao) {

    fun getAllProductWithDeposit(): LiveData<List<DepositWithProduct>> {
        return productInDepositDao.getDepositWithProduct()
    }

    fun filterProduct(idDeposit: Long): LiveData<List<DepositWithProduct>> =
        productInDepositDao.filterProduct(idDeposit)

    suspend fun getTotalSoldProduct(idDeposit: Long): Long {
        return productInDepositDao.getTotalSoldProduct(idDeposit)
    }

    suspend fun insertData(productInDeposit: ProductInDeposit) {
        productInDepositDao.insertData(productInDeposit)
    }

    suspend fun updateData(productInDeposit: ProductInDeposit) {
        productInDepositDao.updateData(productInDeposit)
    }

    suspend fun deleteItem(productInDeposit: ProductInDeposit) {
        productInDepositDao.deleteItem(productInDeposit)
    }

    suspend fun getProductById(id: Long): ProductInDeposit? {
        return productInDepositDao.getProductById(id)
    }
}