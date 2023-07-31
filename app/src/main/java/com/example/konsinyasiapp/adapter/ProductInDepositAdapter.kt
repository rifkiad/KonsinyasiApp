package com.example.konsinyasiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.databinding.ItemProductInDepositBinding
import com.example.konsinyasiapp.entities.DepositWithProduct
import com.example.konsinyasiapp.utils.ProductInDeposiDiffCallback

class ProductInDepositAdapter : RecyclerView.Adapter<ProductInDepositAdapter.MyViewHolder>() {

    private var depositWithProduct = listOf<DepositWithProduct>()

    inner class MyViewHolder(private val binding: ItemProductInDepositBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(depositWithProduct: DepositWithProduct) {
            binding.depositWithProduct = depositWithProduct
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemProductInDepositBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return depositWithProduct.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(depositWithProduct[position])
    }

    fun setData(dataProduct: List<DepositWithProduct>) {
        val productDiffUtil = ProductInDeposiDiffCallback(depositWithProduct, dataProduct)
        val productDiffUtilResult = DiffUtil.calculateDiff(productDiffUtil)
        this.depositWithProduct = dataProduct
        productDiffUtilResult.dispatchUpdatesTo(this)
    }
}