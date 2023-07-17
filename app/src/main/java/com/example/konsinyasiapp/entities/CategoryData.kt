package com.example.konsinyasiapp.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "category_table")
@Parcelize
class CategoryData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var nameCategory: String


    ) : Parcelable