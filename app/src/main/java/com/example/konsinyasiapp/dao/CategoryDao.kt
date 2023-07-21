package com.example.konsinyasiapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.konsinyasiapp.entities.CategoryData

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category_table ORDER BY id_category ASC")
    fun getAllCategory(): LiveData<List<CategoryData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(categoryData: CategoryData)

    @Delete
    suspend fun deleteItem(categoryData: CategoryData)

    @Query("DELETE FROM category_table")
    suspend fun deleteAll()

}