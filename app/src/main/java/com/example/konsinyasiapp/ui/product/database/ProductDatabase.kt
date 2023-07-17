package com.example.konsinyasiapp.ui.product.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.konsinyasiapp.ui.categoryProduct.database.dao.CategoryDao
import com.example.konsinyasiapp.ui.categoryProduct.database.entities.CategoryData
import com.example.konsinyasiapp.ui.product.database.dao.ProductDao
import com.example.konsinyasiapp.ui.product.database.entities.ProductData

@Database(entities = [ProductData::class, CategoryData::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao

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
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}