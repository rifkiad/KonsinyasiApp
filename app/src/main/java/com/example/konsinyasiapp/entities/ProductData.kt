package com.example.konsinyasiapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "product_table",
    foreignKeys = [
        ForeignKey(
            entity = CategoryData::class,
            parentColumns = ["id"],
            childColumns = ["idKategoriProduk"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ProductData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "idKategoriProduk")
    val idKategoriProduk: Int,
    val nama: String,
    val harga: Double
)
