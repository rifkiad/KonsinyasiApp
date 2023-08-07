package com.example.konsinyasiapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.konsinyasiapp.entities.DepositData
import com.example.konsinyasiapp.entities.DepositWithProduct
import com.example.konsinyasiapp.entities.DepositWithShop

@Dao
interface DepositDao {

    @Query("SELECT * FROM deposit_table ORDER BY id_deposit ASC")
    fun getAllDeposit(): LiveData<List<DepositData>>

    @Transaction
    @Query("SELECT * FROM deposit_table")
    fun getDepositWithShop(): LiveData<List<DepositWithShop>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(depositData: DepositData): Long

    @Update
    suspend fun updateData(depositData: DepositData)

    @Delete
    suspend fun deleteItem(depositData: DepositData)

    @Query("DELETE FROM deposit_table")
    suspend fun deleteAll()
}
