package com.example.konsinyasiapp.ui.shop.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.database.ShopData
import com.example.konsinyasiapp.databinding.ItemShopBinding
import com.example.konsinyasiapp.helper.ShopDiffCallback

class ShopAdapter : RecyclerView.Adapter<ShopAdapter.MyViewHolder>() {

    var dataShop = emptyList<ShopData>()

    class MyViewHolder(private val binding: ItemShopBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(shopData: ShopData) {
            //binding.titleTxt.text = toDoData.title
            //binding.descriptionTxt.text = toDoData.description
            binding.shopData = shopData
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemShopBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return dataShop.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataShop[position]
        holder.bind(currentItem)
    }

    fun setData(data: List<ShopData>) {
        val toDoDiffUtil = ShopDiffCallback(dataShop, data)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.dataShop = data
        toDoDiffResult.dispatchUpdatesTo(this)
    }
}