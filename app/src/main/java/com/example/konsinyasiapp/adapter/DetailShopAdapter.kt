package com.example.konsinyasiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.databinding.ItemDepositDateBinding
import com.example.konsinyasiapp.entities.DepositWithShop
import com.example.konsinyasiapp.utils.ShopInDepositDiffCallback

class DetailShopAdapter : RecyclerView.Adapter<DetailShopAdapter.DetailViewHolder>() {

    private var shopWithDeposit = listOf<DepositWithShop>()


    inner class DetailViewHolder(private val binding: ItemDepositDateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(depositWithShop: DepositWithShop) {
            binding.depositWithShop = depositWithShop
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding =
            ItemDepositDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(shopWithDeposit[position])
    }

    override fun getItemCount(): Int {
        return shopWithDeposit.size
    }

    fun setData(dataDeposit: List<DepositWithShop>) {
        val depositDiffUtil = ShopInDepositDiffCallback(shopWithDeposit, dataDeposit)
        val productDiffUtilResult = DiffUtil.calculateDiff(depositDiffUtil)
        this.shopWithDeposit = dataDeposit
        productDiffUtilResult.dispatchUpdatesTo(this)
    }
}