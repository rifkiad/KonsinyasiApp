package com.example.konsinyasiapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ShopDao {
    @Query("SELECT * FROM shop_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<ShopData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(shopData: ShopData)

    @Update
    suspend fun updateData(shopData: ShopData)

    @Delete
    suspend fun deleteItem(shopData: ShopData)
}