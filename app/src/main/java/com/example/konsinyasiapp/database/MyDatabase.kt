package com.example.konsinyasiapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.konsinyasiapp.dao.CategoryDao
import com.example.konsinyasiapp.dao.DepositDao
import com.example.konsinyasiapp.dao.ProductDao
import com.example.konsinyasiapp.dao.ShopDao
import com.example.konsinyasiapp.entities.CategoryData
import com.example.konsinyasiapp.entities.DepositData
import com.example.konsinyasiapp.entities.ProductData
import com.example.konsinyasiapp.entities.ShopData

@Database(entities = [ProductData::class, CategoryData::class, ShopData::class, DepositData::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
    abstract fun shopDao(): ShopDao
    abstract fun depositDao(): DepositDao

    companion object {

        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"

                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}