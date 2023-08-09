package com.example.konsinyasiapp.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
@Entity(tableName = "product_deposit_with_product")
data class DepositWithProduct(
    @Embedded val productInDeposit: ProductInDeposit,
    @Relation(
        parentColumn = "id_product",
        entityColumn = "product_id"
    )
    val productData: ProductData? = null
)
