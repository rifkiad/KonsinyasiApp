package com.example.konsinyasiapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.konsinyasiapp.entities.ProductData

@Dao
interface ProductDao {

    @Query("SELECT * FROM product_table WHERE idKategoriProduk = :idKategoriProduk")
    fun getAllProduct(idKategoriProduk: Int): LiveData<List<ProductData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(productData: ProductData)

    @Update
    suspend fun updateData(productData: ProductData)

    @Delete
    suspend fun deleteItem(productData: ProductData)

    @Query("DELETE FROM category_table")
    suspend fun deleteAll()
}