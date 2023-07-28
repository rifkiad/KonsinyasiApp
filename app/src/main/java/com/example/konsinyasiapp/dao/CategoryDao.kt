package com.example.konsinyasiapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.konsinyasiapp.entities.CategoryData
import com.example.konsinyasiapp.entities.ProductData

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category_table ORDER BY id_category ASC")
    fun getAllCategory(): LiveData<List<CategoryData>>

    @Query("SELECT id_category FROM category_table WHERE name_category = :categoryName")
    suspend fun getCategoryIdByName(categoryName: String): Int?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(categoryData: CategoryData)

    @Delete
    suspend fun deleteItem(categoryData: CategoryData)

    @Update
    suspend fun updateData(categoryData: CategoryData)

    @Query("DELETE FROM category_table")
    suspend fun deleteAll()

}