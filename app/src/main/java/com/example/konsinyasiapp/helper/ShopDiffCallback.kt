package com.example.konsinyasiapp.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.konsinyasiapp.database.ShopData

class ShopDiffCallback(private val oldShopList: List<ShopData>, private val newShopList: List<ShopData>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldShopList.size
    override fun getNewListSize(): Int = newShopList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldShopList[oldItemPosition].id == newShopList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldShop = oldShopList[oldItemPosition]
        val newShop = newShopList[newItemPosition]
        return oldShop.name == newShop.name && oldShop.address == newShop.address && oldShop.ownerName == newShop.ownerName && oldShop.phoneNumber == newShop.phoneNumber
    }
}