package com.example.konsinyasiapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.konsinyasiapp.entities.ProductData

class ProductDiffCallbackNew(
    private val oldProductList: List<ProductData>,
    private val newProductList: List<ProductData>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldProductList.size
    override fun getNewListSize(): Int = newProductList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldProductList[oldItemPosition].id == newProductList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct = oldProductList[oldItemPosition]
        val newProduct = newProductList[newItemPosition]
        return oldProduct.namaProduct == newProduct.namaProduct && oldProduct.hargaProduct == newProduct.hargaProduct
    }
}