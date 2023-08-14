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

    private lateinit var onItemClickCallback: OnItemClickCallback
    private lateinit var detailBinding: FragmentDetailDepositBinding

    fun setDetailBinding(binding: FragmentDetailDepositBinding) {
        this.detailBinding = binding
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class MyViewHolder(private val binding: ItemProductInDepositDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(depositWithProduct: DepositWithProduct) {
            binding.also {
                val id = depositWithProduct.productInDeposit.id
                val idDeposit = depositWithProduct.productInDeposit.idDeposit
                val idProduct = depositWithProduct.productInDeposit.productId
                val quantity = depositWithProduct.productInDeposit.jumlahQuantity
                var returnQuantity = depositWithProduct.productInDeposit.returnQuantity

                it.tvProductName.text = depositWithProduct.productData?.namaProduct
                it.tvJumlahProductInDepositDetail.text =
                    itemView.context.getString(R.string.format_show_list, quantity.toString())
                it.tvJumlahProductKembaliInDepositDetail.text =
                    itemView.context.getString(R.string.format_show_list, returnQuantity.toString())

                val isEmpty: Boolean
                val valueReturnQuantity = binding.etReturnQuantity.text.toString()
                if (valueReturnQuantity.isNotEmpty()) {
                    returnQuantity = valueReturnQuantity.toLong()
                    isEmpty = false
                } else {
                    isEmpty = true
                }
                //returnQuantity = if (valueReturnQuantity.isEmpty()) 0 else valueReturnQuantity.toInt()
                depositWithProduct.productInDeposit.returnQuantity = returnQuantity

                val productDeposit = ProductInDeposit(
                    id = id,
                    idDeposit = idDeposit,
                    productId = idProduct,
                    jumlahQuantity = quantity,
                    returnQuantity = returnQuantity
                )
                onItemClickCallback.onButtonUpdateQuantity(productDeposit, isEmpty)

                binding.etReturnQuantity.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    }

                    override fun afterTextChanged(s: Editable?) {
                        val newValue = s.toString()
                        if (newValue.isNotEmpty()) {
                            val newQuantity = newValue.toLong()
                            depositWithProduct.productInDeposit.returnQuantity = newQuantity
                        }
                    }
                })
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