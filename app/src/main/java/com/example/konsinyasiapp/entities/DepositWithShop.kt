package com.example.konsinyasiapp.entities

import androidx.room.Embedded
import androidx.room.Relation

class DepositWithShop(
    @Embedded val depositData: DepositData,
    @Relation(
        parentColumn = "shop_id",
        entityColumn = "id_shop"
    )
    val shopData: ShopData? = null
)