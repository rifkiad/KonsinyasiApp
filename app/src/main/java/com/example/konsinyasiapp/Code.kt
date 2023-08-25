package com.example.konsinyasiapp

class Code {
//&& it.productInDeposit.returnQuantity.toString().isNotEmpty()

//    class ShopAdapter(
//        val context: Context,
//        var userList: ArrayList<ShopData>,
//        val viewModel: ShopViewModel
//    ) :
//        RecyclerView.Adapter<ShopAdapter.MyViewHolder>() {
//
//        inner class MyViewHolder(private val binding: ItemShopBinding) :
//            RecyclerView.ViewHolder(binding.root) {
//
//            init {
//                binding.mMenus.setOnClickListener {
//                    popupMenus(it)
//                }
//                itemView.setOnClickListener { view ->
//                    val action =
//                        ShopFragmentDirections.actionNavShopToActionNavShopDetail(userList[adapterPosition])
//                    view.findNavController().navigate(action)
//                }
//            }
//
//            fun bind(shopData: ShopData) {
//                binding.shopData = shopData
//                binding.executePendingBindings()
//            }
//
//            private fun popupMenus(v: View) {
//                val position = userList[adapterPosition]
//                val popupMenus = PopupMenu(context, v)
//                popupMenus.inflate(R.menu.shop_fragment_menu_more)
//                popupMenus.setOnMenuItemClickListener { item ->
//                    when (item.itemId) {
//                        R.id.edit_text -> {
//                            showEditDialog(position)
//                            true
//                        }
//
//                        R.id.delete -> {
//                            showDeleteDialog(position)
//                            true
//                        }
//
//                        else -> false
//                    }
//                }
//                popupMenus.show()
//                val popup = PopupMenu::class.java.getDeclaredField("mPopup")
//                popup.isAccessible = true
//                val menu = popup.get(popupMenus)
//                menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
//                    .invoke(menu, true)
//            }
//
//            private fun showEditDialog(shopData: ShopData) {
//                val binding = EditShopBinding.inflate(LayoutInflater.from(context))
//                val name = binding.edtNamaTokoEdit
//                val address = binding.edtAlamatEdit
//                val ownerShop = binding.edtNamaPemilikEdit
//                val phoneNumber = binding.edtNomorTeleponEdit
//
//                name.setText(shopData.name)
//                address.setText(shopData.address)
//                ownerShop.setText(shopData.ownerName)
//                phoneNumber.setText(shopData.phoneNumber)
//
//                val dialogView = binding.root
//
//                val dialogBuilder = AlertDialog.Builder(context)
//                    .setTitle("Edit Toko")
//                    .setView(dialogView)
//                    .setPositiveButton("Ok", null)
//                    .setNegativeButton("Cancel", null)
//
//                val dialog = dialogBuilder.create()
//                dialog.setOnShowListener {
//                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
//                        shopData.name = name.text.toString()
//                        shopData.address = address.text.toString()
//                        shopData.ownerName = ownerShop.text.toString()
//                        shopData.phoneNumber = phoneNumber.text.toString()
//
//                        viewModel.updateData(shopData)
//
//                        dialog.dismiss()
//                    }
//                }
//
//                dialog.show()
//            }
//
//            private fun showDeleteDialog(shopData: ShopData) {
//                AlertDialog.Builder(context)
//                    .setTitle("Hapus")
//                    .setIcon(R.drawable.ic_warning)
//                    .setMessage("Apa Kamu Yakin Ingin Menghapus Item Toko Ini?")
//                    .setPositiveButton("Ya") { dialog, _ ->
//                        viewModel.deleteItem(shopData)
//
//                        userList.remove(shopData)
//                        notifyItemRemoved(adapterPosition)
//                        Toast.makeText(context, "Informasi Terhapus", Toast.LENGTH_SHORT).show()
//
//                        dialog.dismiss()
//                    }
//                    .setNegativeButton("Tidak") { dialog, _ ->
//                        dialog.dismiss()
//                    }
//                    .create()
//                    .show()
//            }
//        }
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//            val binding = ItemShopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//            return MyViewHolder(binding)
//        }
//
//        override fun getItemCount(): Int {
//            return userList.size
//        }
//
//        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//            val currentItem = userList[position]
//            holder.bind(currentItem)
//        }
//
//        fun setData(shopData: ArrayList<ShopData>) {
//            val shopDiffUtil = ShopDiffCallback(userList, shopData)
//            val shopDiffUtilResult = DiffUtil.calculateDiff(shopDiffUtil)
//            userList.clear()
//            userList.addAll(shopData)
//            shopDiffUtilResult.dispatchUpdatesTo(this)
//        }
//    }

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

//    private fun setupListeners() {
//        binding.btnDetailDepositProduk.setOnClickListener {
//            if (isDataComplete()) {
//                listData.forEach { depositWithProduct ->
//                    depositViewModel.updateData(depositWithProduct.productInDeposit)
//                }
//                val action = DetailDepositFragmentDirections.actionDepositDetailToRincianDeposit(
//                    idDeposit = args.currentItem.depositData.id,
//                    currentItem = args.currentItem
//                )
//                findNavController().navigate(action)
//            } else {
//                Snackbar.make(
//                    requireView(),
//                    "Isi Dulu Produk Yang Kembali",
//                    Snackbar.LENGTH_SHORT
//                ).show()
//            }
//
//            // Tambahkan bagian ini sebagai pernyataan if terpisah
//            for (data in listData) {
//                if (data.productInDeposit.returnQuantity > data.productInDeposit.jumlahQuantity) {
//                    Snackbar.make(requireView(), "bebas", Snackbar.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
}
