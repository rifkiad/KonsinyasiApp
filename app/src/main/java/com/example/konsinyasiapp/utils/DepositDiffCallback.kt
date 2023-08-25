package com.example.konsinyasiapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.konsinyasiapp.entities.DepositData

class DepositDiffCallback(
    private val oldDepositList: List<DepositData>,
    private val newDepositList: List<DepositData>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldDepositList.size
    override fun getNewListSize(): Int = newDepositList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldDepositList[oldItemPosition].id == newDepositList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldDeposit = oldDepositList[oldItemPosition]
        val newDeposit = newDepositList[newItemPosition]
        return oldDeposit.shopId == newDeposit.shopId && oldDeposit.depositDate == newDeposit.depositDate && oldDeposit.depositFinish == newDeposit.depositFinish && oldDeposit.statusDeposit == newDeposit.statusDeposit
    }
}