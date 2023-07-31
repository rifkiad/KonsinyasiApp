package com.example.konsinyasiapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.konsinyasiapp.entities.DepositWithShop

class ShopInDepositDiffCallback(
    private val oldProductList: List<DepositWithShop>,
    private val newProductList: List<DepositWithShop>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldProductList.size
    override fun getNewListSize(): Int = newProductList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldProductList[oldItemPosition].depositData.id == newProductList[newItemPosition].depositData.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct = oldProductList[oldItemPosition]
        val newProduct = newProductList[newItemPosition]
        return oldProduct.depositData.depositDate == newProduct.depositData.depositDate && oldProduct.depositData.depositFinish == newProduct.depositData.depositFinish
    }
}