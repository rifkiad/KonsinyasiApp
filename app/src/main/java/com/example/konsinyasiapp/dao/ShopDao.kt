package com.example.konsinyasiapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.konsinyasiapp.entities.DepositData
import com.example.konsinyasiapp.entities.ShopData

@Dao
interface ShopDao {
    @Query("SELECT * FROM shop_table ORDER BY id_shop ASC")
    fun getAllData(): LiveData<List<ShopData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(shopData: ShopData)

    @Update
    suspend fun updateData(shopData: ShopData)

    @Delete
    suspend fun deleteItem(shopData: ShopData)

    @Query("DELETE FROM shop_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM deposit_table WHERE shop_id= :shopId")
    fun getAllDepositInStore(shopId: Int): LiveData<List<DepositData>>
}