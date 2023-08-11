package com.example.konsinyasiapp

import androidx.databinding.BindingConversion
import androidx.databinding.InverseMethod

object ConvertersBinding {
    @JvmStatic
    @BindingConversion
    fun intToString(value: Int): String {
        return value.toString()
    }

    @JvmStatic
    @InverseMethod("intToString")
    fun stringToInt(value: String): Int {
        return try {
            value.toInt()
        } catch (e: NumberFormatException) {
            0
        }
    }
}