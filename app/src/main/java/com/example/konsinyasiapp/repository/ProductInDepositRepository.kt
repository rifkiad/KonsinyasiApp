package com.example.konsinyasiapp.repository

import androidx.lifecycle.LiveData
import com.example.konsinyasiapp.dao.ProductInDepositDao
import com.example.konsinyasiapp.entities.DepositWithProduct
import com.example.konsinyasiapp.entities.ProductInDeposit

class ProductInDepositRepository(private val productInDepositDao: ProductInDepositDao) {

    val getAllDeposit: LiveData<List<ProductInDeposit>> = productInDepositDao.getAllDeposit()

    fun getAllProductWithDeposit(): LiveData<List<DepositWithProduct>> {
        return productInDepositDao.getDepositWithProduct()
    }

    fun filterProduct(idProduct: Int): LiveData<List<DepositWithProduct>> =
        productInDepositDao.filterProduct(idProduct)

    suspend fun insertData(productInDeposit: ProductInDeposit) {
        productInDepositDao.insertData(productInDeposit)
    }

    suspend fun updateData(productInDeposit: ProductInDeposit) {
        productInDepositDao.updateData(productInDeposit)
    }

    suspend fun deleteItem(productInDeposit: ProductInDeposit) {
        productInDepositDao.deleteItem(productInDeposit)
    }
}