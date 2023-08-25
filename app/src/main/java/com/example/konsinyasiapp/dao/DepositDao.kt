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
import com.example.konsinyasiapp.entities.ProductInDeposit
import com.example.konsinyasiapp.entities.ProductInDepositWithProduct

@Dao
interface DepositDao {

    @Query("SELECT * FROM deposit_table ORDER BY id_deposit ASC")
    fun getAllDeposit(): LiveData<List<DepositData>>

    @Query("SELECT * FROM deposit_table")
    fun getAllDepositHome(): LiveData<List<DepositData>>

    @Transaction
    @Query("SELECT * FROM deposit_product_table")
    fun getAllDepositProduct(): LiveData<List<ProductInDepositWithProduct>>

    @Transaction
    @Query("SELECT * FROM deposit_table")
    fun getDepositWithShop(): LiveData<List<DepositWithShop>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(depositData: DepositData): Long

    @Update
    suspend fun updateData(productInDepositData: ProductInDeposit)

    @Delete
    suspend fun deleteItem(depositData: DepositData)

    @Query("DELETE FROM deposit_table")
    suspend fun deleteAll()

    @Query("UPDATE deposit_table SET deposit_finish_date = :date WHERE id_deposit = :depositId")
    suspend fun updateDepositFinishDate(depositId: Long, date: String)

    @Transaction
    @Query("SELECT * FROM deposit_table WHERE shop_id = :shopId")
    fun getDepositWithShopForShop(shopId: Long): LiveData<List<DepositWithShop>>

    @Transaction
    @Query("SELECT * FROM deposit_table WHERE status_deposit = 'DEPOSIT'")
    fun getAllUnfinishedDeposit(): LiveData<List<DepositWithShop>>
}
