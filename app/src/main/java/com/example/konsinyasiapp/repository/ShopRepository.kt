package com.example.konsinyasiapp.repository

import androidx.lifecycle.LiveData
import com.example.konsinyasiapp.dao.ShopDao
import com.example.konsinyasiapp.entities.ShopData

class ShopRepository(private val shopDao: ShopDao) {

    val getAllData: LiveData<List<ShopData>> = shopDao.getAllData()

    suspend fun insertData(shopData: ShopData) {
        shopDao.insertData(shopData)
    }

    suspend fun deleteAll() {
        shopDao.deleteAll()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<ShopData>> {
        return shopDao.searchDatabase(searchQuery)
    }
}