package com.example.konsinyasiapp

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel

class SharedViewModel(application: Application) : AndroidViewModel(application)  {

     fun verifyDataFromUser(
        name: String,
        address: String,
        productOwner: String,
        phoneNumber: String
    ): Boolean {
        return if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(
                productOwner
            ) || TextUtils.isEmpty(phoneNumber)
        ) {
            false
        } else !(name.isEmpty() || address.isEmpty() || productOwner.isEmpty() || phoneNumber.isEmpty())
    }

    fun verifyDataFromCategory(
        name: String,
    ): Boolean {
        return if (TextUtils.isEmpty(name)
        ) {
            false
        } else !(name.isEmpty())
    }
}