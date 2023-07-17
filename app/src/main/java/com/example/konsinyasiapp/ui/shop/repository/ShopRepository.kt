package com.example.konsinyasiapp.ui.shop.repository

import androidx.lifecycle.LiveData
import com.example.konsinyasiapp.ui.shop.database.dao.ShopDao
import com.example.konsinyasiapp.ui.shop.database.entities.ShopData

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