package com.example.konsinyasiapp

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



}