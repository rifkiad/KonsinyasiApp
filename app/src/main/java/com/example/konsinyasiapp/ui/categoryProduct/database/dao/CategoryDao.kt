package com.example.konsinyasiapp.ui.categoryProduct.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.konsinyasiapp.ui.categoryProduct.database.entities.CategoryData

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category_table ORDER BY id ASC")
    fun getAllCategory(): LiveData<List<CategoryData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(categoryData: CategoryData)

    @Delete
    suspend fun deleteItem(categoryData: CategoryData)

    @Query("DELETE FROM category_table")
    suspend fun deleteAll()

}