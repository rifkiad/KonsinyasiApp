package com.example.konsinyasiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.databinding.ItemProductBinding
import com.example.konsinyasiapp.entities.ProductData
import com.example.konsinyasiapp.entities.ProductWithCategory
import com.example.konsinyasiapp.utils.ProductDiffCallback

class ProductAdapter(var dataProduct: (ProductData) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    private var productWithCategory = listOf<ProductWithCategory>()

    inner class MyViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(productWithCategory: ProductWithCategory) {
            binding.productWithCategory = productWithCategory
            binding.executePendingBindings()
            //            binding.also {
//                it.tvHargaProduct.text =
//                    productWithCategory.productData.hargaProduct
//                it.tvNamaProduct.text = productWithCategory.productData.namaProduct
//                it.tvPilihanProduct.text =
//                    productWithCategory.categoryData?.nameCategory
//            }

            binding.mMenus.setOnClickListener {
                dataProduct(productWithCategory.productData)
            }
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



