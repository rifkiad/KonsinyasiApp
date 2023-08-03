package com.example.konsinyasiapp.entities

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Entity(tableName = "deposit_shop_table")
@Parcelize
class DepositWithShop(
    @Embedded val depositData: DepositData,
    @Relation(
        parentColumn = "shop_id",
        entityColumn = "id_shop"
    )
    val shopData: ShopData? = null
): Parcelable