package com.example.konsinyasiapp.ui.categoryProduct.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.databinding.ItemCategoryBinding
import com.example.konsinyasiapp.ui.categoryProduct.database.entities.CategoryData


class CategoryAdapter(private val onDeleteItemClick: (deletedItem: CategoryData, categoryData: CategoryData) -> Unit)  : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    var dataCategory = emptyList<CategoryData>()

    inner class MyViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDeleteItemClick(dataCategory[position], dataCategory[position])
                }
            }
        }

        fun bind(categoryData: CategoryData) {
            binding.categoryData = categoryData
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataCategory.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataCategory[position]
        holder.bind(currentItem)
    }

    fun setData(data: List<CategoryData>) {
        val categoryDiffUtil = CategoryDiffCallback(dataCategory, data)
        val categoryDiffUtilResult = DiffUtil.calculateDiff(categoryDiffUtil)
        this.dataCategory = data
        categoryDiffUtilResult.dispatchUpdatesTo(this)
    }
}