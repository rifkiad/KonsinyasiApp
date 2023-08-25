package com.example.konsinyasiapp.repository

import androidx.lifecycle.LiveData
import com.example.konsinyasiapp.dao.ShopDao
import com.example.konsinyasiapp.entities.DepositData
import com.example.konsinyasiapp.entities.ShopData

class ShopRepository(private val shopDao: ShopDao) {

    val getAllData: LiveData<List<ShopData>> = shopDao.getAllData()

    suspend fun insertData(shopData: ShopData) {
        shopDao.insertData(shopData)
    }

    suspend fun updateData(shopData: ShopData) {
        shopDao.updateData(shopData)
    }

    suspend fun deleteItem(shopData: ShopData) {
        shopDao.deleteItem(shopData)
    }

    suspend fun deleteAll() {
        shopDao.deleteAll()
    }

    fun getAllDepositInStore(shopId: Int): LiveData<List<DepositData>> =
        shopDao.getAllDepositInStore(shopId)
}
