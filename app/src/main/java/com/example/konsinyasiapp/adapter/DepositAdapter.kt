package com.example.konsinyasiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.databinding.ItemDepositBinding
import com.example.konsinyasiapp.entities.DepositData
import com.example.konsinyasiapp.entities.DepositWithShop
import com.example.konsinyasiapp.entities.ProductWithCategory
import com.example.konsinyasiapp.ui.deposit.DepositFragmentDirections
import com.example.konsinyasiapp.ui.deposit.DetailDepositFragment
import com.example.konsinyasiapp.ui.shop.ShopFragmentDirections
import com.example.konsinyasiapp.utils.ProductDiffCallback
import com.example.konsinyasiapp.utils.ShopDiffCallback
import com.example.konsinyasiapp.utils.ShopInDepositDiffCallback

class DepositAdapter(var dataDeposit: (DepositData) -> Unit) :
    RecyclerView.Adapter<DepositAdapter.MyViewHolder>() {

    private var shopWithDeposit = listOf<DepositWithShop>()

    private var depositData: DepositData? = null

    inner class MyViewHolder(private val binding: ItemDepositBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(depositWithShop: DepositWithShop) {
            binding.depositWithShop = depositWithShop
            binding.executePendingBindings()

            itemView.setOnClickListener { view ->
                val depositData = DepositData(
                    id = depositWithShop.depositData.id,
                    shopId = depositWithShop.depositData.shopId,
                    depositDate = depositWithShop.depositData.depositDate,
                    depositFinish = depositWithShop.depositData.depositFinish
                )
                val action = DepositFragmentDirections.actionNavDepositToDepositDetail(depositWithShop)
                view.findNavController().navigate(action)
                dataDeposit(depositData)
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

//depositData = depositData?.let {
//    DepositData(
//        id = it.id,
//        shopId = it.shopId,
//        depositDate = it.depositDate,
//        depositFinish = it.depositFinish
//    )
//}
//
//itemView.setOnClickListener { view ->
//    depositData?.let { data ->
//        dataDeposit(data)