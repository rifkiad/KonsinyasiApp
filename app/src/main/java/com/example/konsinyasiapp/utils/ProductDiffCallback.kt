package com.example.konsinyasiapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.konsinyasiapp.entities.ProductWithCategory

class ProductDiffCallback(
    private val oldProductList: List<ProductWithCategory>,
    private val newProductList: List<ProductWithCategory>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldProductList.size
    override fun getNewListSize(): Int = newProductList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldProductList[oldItemPosition].productData.id == newProductList[newItemPosition].productData.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct = oldProductList[oldItemPosition]
        val newProduct = newProductList[newItemPosition]
        return oldProduct.productData.namaProduct == newProduct.productData.namaProduct && oldProduct.productData.hargaProduct == newProduct.productData.hargaProduct
    }
}