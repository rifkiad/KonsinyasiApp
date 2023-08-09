package com.example.konsinyasiapp.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "deposit_product_table")
@Parcelize
class ProductInDeposit(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_deposit_product")
    var id: Int,

    @ColumnInfo(name = "id_product")
    var productId: Long,

    @ColumnInfo(name = "id_deposit")
    var idDeposit: Long,

    @ColumnInfo(name = "quantity")
    var jumlahQuantity: String,

    @ColumnInfo(name = "return_quantity")
    var returnQuantity: Int

) : Parcelable