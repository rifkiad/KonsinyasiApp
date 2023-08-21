package com.example.konsinyasiapp

import androidx.room.TypeConverter
import com.example.konsinyasiapp.entities.StatusDeposit

class ConverterDeposit {

    @TypeConverter
    fun fromStatusDeposit(statusDeposit: StatusDeposit) : String {
        return statusDeposit.name
    }

    @TypeConverter
    fun toStatusDeposit(statusDeposit: String) : StatusDeposit {
        return StatusDeposit.valueOf(statusDeposit)
    }
}