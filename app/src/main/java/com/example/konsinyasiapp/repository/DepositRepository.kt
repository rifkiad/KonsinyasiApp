package com.example.konsinyasiapp.repository

import androidx.lifecycle.LiveData
import com.example.konsinyasiapp.dao.DepositDao
import com.example.konsinyasiapp.entities.DepositData
import com.example.konsinyasiapp.entities.DepositWithProduct
import com.example.konsinyasiapp.entities.DepositWithShop
import com.example.konsinyasiapp.entities.ProductInDeposit

class DepositRepository(private val depositDao: DepositDao) {

    val getAllDeposit: LiveData<List<DepositData>> = depositDao.getAllDeposit()

    fun getAllShopWithDeposit(): LiveData<List<DepositWithShop>> {
        return depositDao.getDepositWithShop()
    }

    suspend fun insertData(depositData: DepositData): Long {
        return depositDao.insertData(depositData)
    }

    suspend fun updateData(productInDepositData: ProductInDeposit) {
        depositDao.updateData(productInDepositData)
    }

    suspend fun deleteItem(depositData: DepositData) {
        depositDao.deleteItem(depositData)
    }

    suspend fun deleteAll() {
        depositDao.deleteAll()
    }
}