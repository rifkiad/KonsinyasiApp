package com.example.konsinyasiapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.konsinyasiapp.entities.DepositWithProduct

class ProductInDeposiDiffCallback(
    private val oldProductList: List<DepositWithProduct>,
    private val newProductList: List<DepositWithProduct>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldProductList.size
    override fun getNewListSize(): Int = newProductList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldProductList[oldItemPosition].productInDeposit.id == newProductList[newItemPosition].productInDeposit.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct = oldProductList[oldItemPosition]
        val newProduct = newProductList[newItemPosition]
        return oldProduct.productInDeposit.jumlahQuantity == newProduct.productInDeposit.jumlahQuantity
    }
}