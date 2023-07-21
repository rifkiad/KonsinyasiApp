package com.example.konsinyasiapp.viewModel

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    var categoryId: Int = 0

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

    fun verifyDataFromProduct(
        name: String,
        addCategory: String,
        priceProduct: String

    ): Boolean {
        return if (TextUtils.isEmpty(name) || TextUtils.isEmpty(addCategory) || TextUtils.isEmpty(
                priceProduct
            )
        ) {
            false
        } else !(name.isEmpty() || name.isEmpty() || addCategory.isEmpty() || priceProduct.isEmpty())
    }
}