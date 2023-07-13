package com.example.konsinyasiapp.ui.categoryProduct.adapter

import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.databinding.ItemCategoryBinding
import com.example.konsinyasiapp.ui.product.database.entities.CategoryData
import com.example.konsinyasiapp.ui.shop.ShopFragmentDirections

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: ItemCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

//        fun bind(categoryData: CategoryData) {
//            binding.categoryData = categoryData
//            binding.executePendingBindings()
//
//            itemView.setOnClickListener { view ->
//                val action = ShopFragmentDirections.actionNavShopToActionNavShopDetail(categoryData)
//                view.findNavController().navigate(action)
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}