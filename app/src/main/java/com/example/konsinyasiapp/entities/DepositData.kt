package com.example.konsinyasiapp.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "deposit_table")
@Parcelize
class DepositData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_deposit")
    var id: Long,

    @ColumnInfo(name = "shop_id")
    var shopId: Int,

    @ColumnInfo(name = "deposit_date")
    var depositDate: String,

    @ColumnInfo(name = "deposit_finish_date")
    var depositFinish: String,

    @ColumnInfo(name = "status_deposit")
    var statusDeposit: StatusDeposit

) : Parcelable

enum class StatusDeposit(val displayString: String) {
    MENUNGGU("MENUNGGU"),
    SELESAI("SELESAI")
}