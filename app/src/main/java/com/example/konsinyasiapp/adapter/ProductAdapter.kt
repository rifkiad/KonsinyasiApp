package com.example.konsinyasiapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.dao.ProductDao
import com.example.konsinyasiapp.databinding.ItemProductBinding
import com.example.konsinyasiapp.entities.ProductData
import com.example.konsinyasiapp.utils.ProductDiffCallback
import com.example.konsinyasiapp.viewModel.ProductViewModel

class ProductAdapter(
    val c: Context,
    var productList: ArrayList<ProductData>,
    val viewModel: ProductViewModel,
) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(productData: ProductData) {
            binding.productData = productData
            binding.executePendingBindings()

            binding.mMenus.setOnClickListener {
                popupMenus(it)
            }
        }

        private fun popupMenus(v: View) {
            val position = adapterPosition
            val productData = productList[position]
            val popupMenus = PopupMenu(c, v)
            popupMenus.inflate(R.menu.shop_fragment_menu_more)
            popupMenus.show()

            popupMenus.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.edit_text -> {
                        // Panggil fungsi updateData di ViewModel
                        viewModel.updateData(productData)
                        true
                    }

                    R.id.delete -> {
                        // Panggil fungsi deleteItem di ViewModel
                        viewModel.deleteItem(productData)
                        true
                    }

                    else -> false
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.bind(currentItem)
    }

    fun setData(productData: ArrayList<ProductData>) {
        val productDiffUtil = ProductDiffCallback(productList, productData)
        val productDiffUtilResult = DiffUtil.calculateDiff(productDiffUtil)
        this.productList = productData
        productDiffUtilResult.dispatchUpdatesTo(this)
    }

}