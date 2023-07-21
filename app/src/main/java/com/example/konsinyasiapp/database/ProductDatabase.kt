package com.example.konsinyasiapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.konsinyasiapp.dao.CategoryDao
import com.example.konsinyasiapp.entities.CategoryData
import com.example.konsinyasiapp.dao.ProductDao
import com.example.konsinyasiapp.entities.ProductData

@Database(entities = [ProductData::class, CategoryData::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao

    companion object {

        @Volatile
        private var INSTANCE: ProductDatabase? = null

        fun getDatabase(context: Context): ProductDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "product_database"

                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}