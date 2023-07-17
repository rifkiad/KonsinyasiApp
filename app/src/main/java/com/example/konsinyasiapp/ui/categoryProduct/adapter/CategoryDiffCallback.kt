package com.example.konsinyasiapp.ui.categoryProduct.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.konsinyasiapp.ui.categoryProduct.database.entities.CategoryData
import com.example.konsinyasiapp.ui.shop.database.entities.ShopData

class CategoryDiffCallback(private val oldCategoryList: List<CategoryData>, private val newCategoryList: List<CategoryData>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldCategoryList.size

    override fun getNewListSize(): Int = newCategoryList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCategoryList[oldItemPosition].id == newCategoryList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCategory = oldCategoryList[oldItemPosition]
        val newCategory = newCategoryList[newItemPosition]
        return oldCategory.nameCategory == newCategory.nameCategory
    }
}