package com.example.konsinyasiapp.ui.categoryProduct.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.konsinyasiapp.ui.categoryProduct.database.dao.CategoryDao
import com.example.konsinyasiapp.ui.product.database.entities.CategoryData

@Database(entities = [CategoryData::class], version = 2, exportSchema = false)
abstract class CategoryDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    companion object {

        @Volatile
        private var INSTANCE: CategoryDatabase? = null

        fun getDatabase(context: Context): CategoryDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CategoryDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}