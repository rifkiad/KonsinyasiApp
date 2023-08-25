package com.example.konsinyasiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.databinding.ItemDepositDateBinding
import com.example.konsinyasiapp.entities.DepositData
import com.example.konsinyasiapp.entities.DepositWithShop
import com.example.konsinyasiapp.utils.DepositDiffCallback
import com.example.konsinyasiapp.utils.ShopInDepositDiffCallback

class DetailShopAdapter : RecyclerView.Adapter<DetailShopAdapter.DetailViewHolder>() {

    private var shopWithDeposit = listOf<DepositData>()

    inner class DetailViewHolder(private val binding: ItemDepositDateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(depositData: DepositData) {
            binding.depositWithShop = depositData
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

    fun setData(dataDeposit: List<DepositData>) {
        val depositDiffUtil = DepositDiffCallback(shopWithDeposit, dataDeposit)
        val depositDiffUtilResult = DiffUtil.calculateDiff(depositDiffUtil)
        shopWithDeposit = dataDeposit
        depositDiffUtilResult.dispatchUpdatesTo(this)
    }
}


