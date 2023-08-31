package com.example.konsinyasiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.databinding.ItemDepositBinding
import com.example.konsinyasiapp.entities.DepositWithShop
import com.example.konsinyasiapp.ui.deposit.DepositFragmentDirections
import com.example.konsinyasiapp.ui.home.HomeFragmentDirections
import com.example.konsinyasiapp.utils.ShopInDepositDiffCallback

class DepositAdapter : RecyclerView.Adapter<DepositAdapter.MyViewHolder>() {

    private var shopWithDeposit = listOf<DepositWithShop>()

    inner class MyViewHolder(private val binding: ItemDepositBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(depositWithShop: DepositWithShop) {
            binding.depositWithShop = depositWithShop
            binding.executePendingBindings()

            itemView.setOnClickListener { view ->
                when (view.findNavController().currentDestination?.id) {
                    R.id.nav_deposit -> {
                        val action = DepositFragmentDirections.actionNavDepositToDepositDetail(depositWithShop, false)
                        view.findNavController().navigate(action)
                    }
                    R.id.nav_home -> {
                        val actionToDetailDeposit = HomeFragmentDirections.actionNavHomeToDepositDetail(depositWithShop, true)
                        view.findNavController().navigate(actionToDetailDeposit)
                    }
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemDepositBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return shopWithDeposit.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(shopWithDeposit[position])
    }

    fun setData(dataDeposit: List<DepositWithShop>) {
        val depositDiffUtil = ShopInDepositDiffCallback(shopWithDeposit, dataDeposit)
        val productDiffUtilResult = DiffUtil.calculateDiff(depositDiffUtil)
        this.shopWithDeposit = dataDeposit
        productDiffUtilResult.dispatchUpdatesTo(this)
    }
}