package com.example.konsinyasiapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.konsinyasiapp.entities.CategoryData
import com.example.konsinyasiapp.entities.ProductData
import com.example.konsinyasiapp.entities.ProductWithCategory
import com.example.konsinyasiapp.entities.ShopData

@Dao
interface ProductDao {
    @Query("SELECT * FROM product_table ORDER BY category_id ASC")
    fun getAllProduct(): LiveData<List<ProductData>>

    @Transaction
    @Query("SELECT * FROM product_table")
    fun getProductsWithCategories(): LiveData<List<ProductWithCategory>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(productData: ProductData)

    @Update
    suspend fun updateData(productData: ProductData)

    @Delete
    suspend fun deleteItem(productData: ProductData)

    @Query("DELETE FROM product_table")
    suspend fun deleteAll()
}