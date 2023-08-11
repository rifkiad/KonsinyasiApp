package com.example.konsinyasiapp

import com.example.konsinyasiapp.adapter.DepositDetailAdapter
import com.example.konsinyasiapp.entities.DepositWithProduct
import com.example.konsinyasiapp.entities.ProductInDeposit

class Code {

    //private fun deleteProduct() {
//
//}
//
//private fun editText(productData: ProductData) {
//    val builder = AlertDialog.Builder(requireContext())
//    builder.setPositiveButton("Ya") { _, _ ->
//        mProductViewModel.updateData(productData)
//        // Panggil fungsi untuk menampilkan dialog edit produk
//        //showDialogEditProduct(dataProduct)
//    }
//    builder.setNegativeButton("Tidak", null)
//    builder.setTitle("Edit Item?")
//    builder.setMessage("Anda akan mengedit item. Lanjutkan? ")
//    builder.create().show()
//}

//private fun showDialogEditProduct(dataProduct: ProductData) {
//    val binding = EditProductBinding.inflate(layoutInflater)
//    val name = binding.edtNamaProdukEdit
//    val categoryItem = binding.autoCompleteTextViewCategoryEdit
//    val price = binding.edtHargaProdukEdit
//
//    // mengeset data akan muncul di edit text
//    name.setText(dataProduct.namaProduct)
//    categoryItem.setText(dataProduct.categoryId.toString())
//    price.setText(dataProduct.hargaProduct)
//
//    val dialogView = binding.root
//
//    val dialogBuilder = AlertDialog.Builder(requireContext())
//        .setTitle("Edit Product")
//        .setView(dialogView)
//        .setPositiveButton("Ok") { dialog, _ ->
//            dataProduct.namaProduct = name.text.toString()
//            dataProduct.categoryId = categoryItem.text.toString().toInt()
//            dataProduct.hargaProduct = price.text.toString()
//
//            // Update data menggunakan Room Database
//            mProductViewModel.updateData(dataProduct)
//            Snackbar.make(
//                requireView(),
//                "Informasi Telah Terupdate",
//                Snackbar.LENGTH_SHORT
//            ).show()
//        }
//        .setNegativeButton("Cancel", null)
//
//    val dialog = dialogBuilder.create()
//    dialog.show()
//}


//    depositDetailAdapter.setOnItemClickCallback(object : DepositDetailAdapter.OnItemClickCallback {
//        override fun onButtonUpdateQuantity(data: ProductInDeposit, isEmpty: Boolean) {
//            if (isEmpty) {
//                requireView().customSnackbar("Jumlah Produk "), )
//            } else if (data.returnQuantity > data.jumlahQuantity) {
//                requireView().customSnackbar(""),)
//            } else {
//
//            }
//        }
//    })

    //            binding.also {
//                val id = depositWithProduct.productInDeposit.id
//                val idDeposit = depositWithProduct.productInDeposit.idDeposit
//                val idProduct = depositWithProduct.productInDeposit.productId
//                val quantity = depositWithProduct.productInDeposit.jumlahQuantity
//                var returnQuantity = depositWithProduct.productInDeposit.returnQuantity


//    fun bind(depositWithProduct: DepositWithProduct) {
//        binding.also {
//            val id = depositWithProduct.productInDeposit.id
//            val idDeposit = depositWithProduct.productInDeposit.idDeposit
//            val idProduct = depositWithProduct.productInDeposit.productId
//            val quantity = depositWithProduct.productInDeposit.jumlahQuantity
//            var returnQuantity = depositWithProduct.productInDeposit.returnQuantity
//
//            it.tvProductName.text = depositWithProduct.productData?.namaProduct
//            it.tvJumlahProductInDepositDetail.text =
//                itemView.context.getString(R.string.format_show_list, quantity.toString())
//            it.tvJumlahProductKembaliInDepositDetail.text =
//                itemView.context.getString(R.string.format_show_list, returnQuantity.toString())
//
//            it.btnDetailDeposit.setOnClickListener {
//                val isEmpty: Boolean
//                val valueReturnQuantity = binding.etReturnQuantity.text.toString()
//                if (valueReturnQuantity.isNotEmpty()) {
//                    returnQuantity = valueReturnQuantity.toInt()
//                    isEmpty = false
//                } else {
//                    isEmpty = true
//                }
//                //returnQuantity = if (valueReturnQuantity.isEmpty()) 0 else valueReturnQuantity.toInt()
//                depositWithProduct.productInDeposit.returnQuantity = returnQuantity
//
//                val productDeposit = ProductInDeposit(
//                    id = id,
//                    idDeposit = idDeposit,
//                    productId = idProduct,
//                    jumlahQuantity = quantity,
//                    returnQuantity = returnQuantity
//                )
//                onItemClickCallback.onButtonUpdateQuantity(productDeposit, isEmpty)
//            }
//        }
//    }
//}



}