package com.example.konsinyasiapp.entities

import androidx.room.Embedded
import androidx.room.Relation

data class DepositWithProduct(
    @Embedded val productInDeposit: ProductInDeposit,
    @Relation(
        parentColumn = "id_product",
        entityColumn = "product_id"
    )
    val productData: ProductData? = null
)
