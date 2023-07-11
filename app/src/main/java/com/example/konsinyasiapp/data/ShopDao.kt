package com.example.konsinyasiapp.data

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

    @Query("DELETE FROM shop_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM shop_table WHERE name LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<ShopData>>
}