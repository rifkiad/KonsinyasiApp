package com.example.konsinyasiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.databinding.ItemProductInDepositDetailBinding
import com.example.konsinyasiapp.entities.DepositWithProduct
import com.example.konsinyasiapp.entities.ProductInDeposit
import com.example.konsinyasiapp.utils.ProductInDepositDiffCallback

class DepositDetailAdapter : RecyclerView.Adapter<DepositDetailAdapter.MyViewHolder>() {

    private var depositDetail = listOf<DepositWithProduct>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class MyViewHolder(private val binding: ItemProductInDepositDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(depositWithProduct: DepositWithProduct) {
            binding.depositWithProduct = depositWithProduct
            binding.executePendingBindings()

            val id = depositWithProduct.productInDeposit.id
            val idDeposit = depositWithProduct.productInDeposit.idDeposit
            val idProduct = depositWithProduct.productInDeposit.productId
            val quantity = depositWithProduct.productInDeposit.jumlahQuantity
            val returnQuantity = depositWithProduct.productInDeposit.returnQuantity

            val productInDeposit = ProductInDeposit(
                id = id,
                idDeposit = idDeposit,
                productId = idProduct,
                jumlahQuantity = quantity,
                returnQuantity = returnQuantity
            )
            //onItemClickCallback.onButtonUpdateQuantity(productInDeposit)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemProductInDepositDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return depositDetail.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(depositDetail[position])
    }

    interface OnItemClickCallback {
        fun onButtonUpdateQuantity(data: ProductInDeposit, isEmpty: Boolean)
    }

    fun setData(dataProduct: List<DepositWithProduct>) {
        val productDiffUtil = ProductInDepositDiffCallback(depositDetail, dataProduct)
        val productDiffUtilResult = DiffUtil.calculateDiff(productDiffUtil)
        this.depositDetail = dataProduct
        productDiffUtilResult.dispatchUpdatesTo(this)
    }

}