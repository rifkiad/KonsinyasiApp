package com.example.konsinyasiapp.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "category_table")
@Parcelize
class CategoryData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_category")
    var id: Int,

    @ColumnInfo(name = "name_category")
    var nameCategory: String


) : Parcelable