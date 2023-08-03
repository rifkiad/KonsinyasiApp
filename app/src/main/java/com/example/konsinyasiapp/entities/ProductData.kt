package com.example.konsinyasiapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    val id: Long,

    @ColumnInfo(name = "category_id")
    var categoryId: Int,

    @ColumnInfo(name = "product_name")
    var namaProduct: String,

    @ColumnInfo(name = "product_harga")
    var hargaProduct: String
)


