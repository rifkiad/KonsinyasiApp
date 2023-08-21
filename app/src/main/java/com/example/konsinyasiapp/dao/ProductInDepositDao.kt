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

    @Query("SELECT * FROM deposit_product_table WHERE id_deposit = :idDeposit")
    fun filterProduct(idDeposit: Long): LiveData<List<DepositWithProduct>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(productInDepositData: ProductInDeposit)

    @Update
    suspend fun updateData(productInDepositData: ProductInDeposit)

    @Delete
    suspend fun deleteItem(productInDepositData: ProductInDeposit)

    @Transaction
    @Query("SELECT * FROM deposit_product_table")
    fun getDepositWithProduct(): LiveData<List<DepositWithProduct>>

    @Query("SELECT * FROM deposit_product_table WHERE id_deposit_product = :id")
    suspend fun getProductById(id: Long): ProductInDeposit?
}