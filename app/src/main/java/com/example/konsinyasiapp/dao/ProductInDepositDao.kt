package com.example.konsinyasiapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.konsinyasiapp.entities.DepositWithProduct
import com.example.konsinyasiapp.entities.ProductInDeposit

@Dao
interface ProductInDepositDao {

    @Query("SELECT * FROM deposit_product_table ORDER BY id_deposit_product ASC")
    fun getAllDeposit(): LiveData<List<ProductInDeposit>>

    @Query("SELECT * FROM deposit_product_table WHERE id_deposit = :idDeposit")
    fun filterProduct(idDeposit: Int): LiveData<List<DepositWithProduct>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(productInDepositData: ProductInDeposit)

    @Update
    suspend fun updateData(productInDepositData: ProductInDeposit)

    @Delete
    suspend fun deleteItem(productInDepositData: ProductInDeposit)

    @Transaction
    @Query("SELECT * FROM deposit_product_table")
    fun getDepositWithProduct(): LiveData<List<DepositWithProduct>>
}