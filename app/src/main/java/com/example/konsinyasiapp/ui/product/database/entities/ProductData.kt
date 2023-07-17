package com.example.konsinyasiapp.ui.product.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.konsinyasiapp.ui.categoryProduct.database.entities.CategoryData

@Entity(tableName = "product_table",
    foreignKeys = [
        ForeignKey(
            entity = CategoryData::class,
            parentColumns = ["id"],
            childColumns = ["id_kategori_produk"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ProductData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "id_kategori_produk")
    val idKategoriProduk: Int,
    val nama: String,
    val harga: Double
)
