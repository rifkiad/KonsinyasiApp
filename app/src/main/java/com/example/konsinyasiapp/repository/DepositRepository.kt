package com.example.konsinyasiapp.repository

import androidx.lifecycle.LiveData
import com.example.konsinyasiapp.dao.DepositDao
import com.example.konsinyasiapp.entities.DepositData
import com.example.konsinyasiapp.entities.DepositWithShop

class DepositRepository(private val depositDao: DepositDao) {

    val getAllDeposit: LiveData<List<DepositData>> = depositDao.getAllDeposit()

    fun getAllShopWithDeposit(): LiveData<List<DepositWithShop>> {
        return depositDao.getDepositWithShop()
    }

    suspend fun insertData(depositData: DepositData) {
        depositDao.insertData(depositData)
    }

    suspend fun updateData(depositData: DepositData) {
        depositDao.updateData(depositData)
    }

    suspend fun deleteItem(depositData: DepositData) {
        depositDao.deleteItem(depositData)
    }
}