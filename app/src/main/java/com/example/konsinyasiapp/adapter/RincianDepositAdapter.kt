package com.example.konsinyasiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.databinding.ItemRincianDepositBinding
import com.example.konsinyasiapp.entities.DepositWithProduct
import com.example.konsinyasiapp.entities.ProductInDeposit
import com.example.konsinyasiapp.utils.ProductInDepositDiffCallback

class RincianDepositAdapter(private var depositRincian: List<DepositWithProduct>) :
    RecyclerView.Adapter<RincianDepositAdapter.MyViewHolder>() {

    private var totalSoldProduct: Long = 0

    fun setTotalSoldProduct(total: Long) {
        totalSoldProduct = total
    }

    inner class MyViewHolder(private val binding: ItemRincianDepositBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(depositWithProduct: DepositWithProduct) {
            binding.apply {
                dataProductInDeposit = depositWithProduct
                executePendingBindings()

                // Mengambil data jumlah produk dari depositWithProduct
                val soldProduct = depositWithProduct.productInDeposit.soldProduct

                // Mengatur nilai TextView
                tvJumlahProdukYangTerjualData.text =
                    itemView.context.getString(R.string.format_show_list, soldProduct.toString())

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRincianDepositBinding.inflate(
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
