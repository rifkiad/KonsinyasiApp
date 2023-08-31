package com.example.konsinyasiapp.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.databinding.FragmentDetailDepositBinding
import com.example.konsinyasiapp.databinding.ItemProductInDepositDetailBinding
import com.example.konsinyasiapp.entities.DepositWithProduct
import com.example.konsinyasiapp.entities.ProductInDeposit
import com.example.konsinyasiapp.utils.ProductInDepositDiffCallback

class DepositDetailAdapter(private var depositDetail: List<DepositWithProduct>) :
    RecyclerView.Adapter<DepositDetailAdapter.MyViewHolder>() {

    private lateinit var detailBinding: FragmentDetailDepositBinding

    fun setDetailBinding(binding: FragmentDetailDepositBinding) {
        this.detailBinding = binding
    }

    inner class MyViewHolder(private val binding: ItemProductInDepositDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(depositWithProduct: DepositWithProduct) {
            val productInDeposit = depositWithProduct.productInDeposit
            val quantity = productInDeposit.jumlahQuantity

            binding.apply {
                tvProductName.text = depositWithProduct.productData?.namaProduct
                tvJumlahProductInDepositDetail.text =
                    itemView.context.getString(R.string.format_show_list, quantity.toString())
                tvJumlahProductKembaliInDepositDetail.text =
                    itemView.context.getString(R.string.format_show_list, productInDeposit.returnQuantity.toString())

                setupReturnQuantityWatcher(binding, depositWithProduct)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductInDepositDetailBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return depositDetail.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(depositDetail[position])
    }

    private fun setupReturnQuantityWatcher(
        binding: ItemProductInDepositDetailBinding,
        depositWithProduct: DepositWithProduct
    ) {
        binding.etReturnQuantity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val newValue = s.toString()
                if (newValue.isNotEmpty()) {
                    val newQuantity = newValue.toInt()
                    depositWithProduct.productInDeposit.returnQuantity = newQuantity
                }
            }
        })
    }

    fun setData(dataProduct: List<DepositWithProduct>) {
        val productDiffUtil = ProductInDepositDiffCallback(depositDetail, dataProduct)
        val productDiffUtilResult = DiffUtil.calculateDiff(productDiffUtil)
        this.depositDetail = dataProduct
        productDiffUtilResult.dispatchUpdatesTo(this)
    }
}