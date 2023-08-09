package com.example.konsinyasiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.databinding.ItemProductInDepositDetailBinding
import com.example.konsinyasiapp.databinding.ItemRincianDepositBinding
import com.example.konsinyasiapp.entities.DepositWithProduct
import com.example.konsinyasiapp.utils.ProductInDepositDiffCallback

class RincianDepositAdapter : RecyclerView.Adapter<RincianDepositAdapter.MyViewHolder>() {

    private var depositRincian = listOf<DepositWithProduct>()

    inner class MyViewHolder(private val binding: ItemRincianDepositBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(depositWithProduct: DepositWithProduct) {
            binding.dataProductInDeposit = depositWithProduct
            binding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemRincianDepositBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return depositRincian.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(depositRincian[position])
    }

    fun setData(dataProduct: List<DepositWithProduct>) {
        val productDiffUtil = ProductInDepositDiffCallback(depositRincian, dataProduct)
        val productDiffUtilResult = DiffUtil.calculateDiff(productDiffUtil)
        this.depositRincian = dataProduct
        productDiffUtilResult.dispatchUpdatesTo(this)
    }

}