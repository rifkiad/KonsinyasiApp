package com.example.konsinyasiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.databinding.ItemProductBinding
import com.example.konsinyasiapp.entities.ProductData
import com.example.konsinyasiapp.entities.ProductWithCategory
import com.example.konsinyasiapp.utils.ProductDiffCallback


class ProductAdapter(
    private val showMorePopupMenu: (ProductData, ImageView) -> Unit
) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    private var productWithCategory = listOf<ProductWithCategory>()

    inner class MyViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.mMenus.setOnClickListener {
                val productData = productWithCategory[adapterPosition].productData
                showMorePopupMenu(productData, binding.mMenus)
            }
        }

        fun bind(productWithCategory: ProductWithCategory) {
            binding.productWithCategory = productWithCategory
            binding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productWithCategory.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(productWithCategory[position])
    }

    fun setData(dataProduct: List<ProductWithCategory>) {
        val productDiffUtil = ProductDiffCallback(productWithCategory, dataProduct)
        val productDiffUtilResult = DiffUtil.calculateDiff(productDiffUtil)
        this.productWithCategory = dataProduct
        productDiffUtilResult.dispatchUpdatesTo(this)
    }
}


//            binding.also {
//                it.tvHargaProduct.text =
//                    productWithCategory.productData.hargaProduct
//                it.tvNamaProduct.text = productWithCategory.productData.namaProduct
//                it.tvPilihanProduct.text =
//                    productWithCategory.categoryData?.nameCategory
//            }

//            binding.mMenus.setOnClickListener {
//                showMorePopupMenu(productWithCategory.productData)
//            }
