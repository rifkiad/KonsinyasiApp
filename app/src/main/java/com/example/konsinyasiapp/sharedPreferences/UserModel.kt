package com.example.konsinyasiapp.sharedPreferences

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    var name: String? = null,
    var address: String? = null,

) : Parcelable