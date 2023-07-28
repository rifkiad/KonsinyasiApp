package com.example.konsinyasiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.databinding.ItemDepositBinding
import com.example.konsinyasiapp.entities.DepositData
import com.example.konsinyasiapp.entities.DepositWithShop

class DepositAdapter(var dataDeposit: (DepositData) -> Unit) :
    RecyclerView.Adapter<DepositAdapter.MyViewHolder>() {

    private var shopWithDeposit = listOf<DepositWithShop>()

    inner class MyViewHolder(private val binding: ItemDepositBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(depositWithShop: DepositWithShop) {
            TODO("")
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
}