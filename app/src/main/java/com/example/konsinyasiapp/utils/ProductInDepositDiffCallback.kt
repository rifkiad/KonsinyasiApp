package com.example.konsinyasiapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.konsinyasiapp.entities.DepositWithProduct

class ProductInDepositDiffCallback(
    private val oldDepositList: List<DepositWithProduct>,
    private val newDepositList: List<DepositWithProduct>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldDepositList.size
    override fun getNewListSize(): Int = newDepositList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldDepositList[oldItemPosition].productInDeposit.id == newDepositList[newItemPosition].productInDeposit.id

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldDeposit = oldDepositList[oldItemPosition]
        val newDeposit = newDepositList[newItemPosition]
        return oldDeposit.productInDeposit.jumlahQuantity == newDeposit.productInDeposit.jumlahQuantity
                && oldDeposit.productInDeposit.jumlahQuantity == newDeposit.productInDeposit.jumlahQuantity
                && oldDeposit.productInDeposit.returnQuantity == newDeposit.productInDeposit.returnQuantity
    }
}