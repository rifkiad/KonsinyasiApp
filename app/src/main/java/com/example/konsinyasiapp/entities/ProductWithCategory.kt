package com.example.konsinyasiapp.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ProductWithCategory(
    @Embedded val productData: ProductData,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id_category"
    )
    val categoryData: CategoryData? = null
)