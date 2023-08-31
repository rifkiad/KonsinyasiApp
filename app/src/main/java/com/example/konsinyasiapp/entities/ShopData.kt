package com.example.konsinyasiapp.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "shop_table")
@Parcelize
class ShopData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_shop")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "address")
    var address: String? = null,

    @ColumnInfo(name = "ownerName")
    var ownerName: String? = null,

    @ColumnInfo(name = "phoneNumber")
    var phoneNumber: String? = null

) : Parcelable {
    fun copy(
        id: Int = this.id,
        name: String? = this.name,
        address: String? = this.address,
        ownerName: String? = this.ownerName,
        phoneNumber: String? = this.phoneNumber
    ): ShopData {
        return ShopData(id, name, address, ownerName, phoneNumber)
    }
}