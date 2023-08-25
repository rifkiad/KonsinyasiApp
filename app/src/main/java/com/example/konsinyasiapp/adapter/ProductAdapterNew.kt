package com.example.konsinyasiapp.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.database.MyDatabase
import com.example.konsinyasiapp.databinding.EditProductBinding
import com.example.konsinyasiapp.databinding.ItemProductBinding
import com.example.konsinyasiapp.entities.ProductData
import com.example.konsinyasiapp.utils.ProductDiffCallbackNew
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductAdapterNew(val c: Context, var userList: ArrayList<ProductData>) :
    RecyclerView.Adapter<ProductAdapterNew.MyViewHolder>() {

    inner class MyViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(productData: ProductData) {
            binding.productData = productData
            binding.executePendingBindings()
            binding.mMenus.setOnClickListener {
                popupMenus(it)
            }
        }

        private fun popupMenus(v: View) {
            val position = userList[adapterPosition]
            val popupMenus = PopupMenu(c, v)
            popupMenus.inflate(R.menu.shop_fragment_menu_more)
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.edit_text -> {
                        val binding = EditProductBinding.inflate(LayoutInflater.from(c))
                        val productName = binding.edtNamaProdukEdit
                        val category = binding.autoCompleteTextViewCategoryEdit
                        val productPrice = binding.edtHargaProdukEdit

                        // mengeset data akan muncul di edit text
                        productName.setText(position.namaProduct)
                        category.setText(position.categoryId)
                        productPrice.setText(position.hargaProduct)

                        val dialogView = binding.root

                        val dialogBuilder = AlertDialog.Builder(c)
                            .setTitle("Edit Toko")
                            .setView(dialogView)
                            .setPositiveButton("Ok", null)
                            .setNegativeButton("Cancel", null)

                        val dialog = dialogBuilder.create()
                        dialog.setOnShowListener {
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                                position.namaProduct = productName.text.toString()
                                position.categoryId = category.text.toString().toInt()
                                position.hargaProduct = productPrice.text.toString()

                                // Update data menggunakan Room Database
                                val productDao = MyDatabase.getDatabase(c).productDao()
                                GlobalScope.launch {
                                    productDao.updateData(position)

                                    withContext(Dispatchers.Main) {
                                        notifyDataSetChanged()
                                        Toast.makeText(
                                            c,
                                            "Informasi Telah TerUpdate",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        dialog.dismiss()
                                    }
                                }
                            }
                        }
                        dialog.show()

                        true
                    }

                    R.id.delete -> {
                        AlertDialog.Builder(c)
                            .setTitle("Hapus")
                            .setIcon(R.drawable.ic_warning)
                            .setMessage("Apa Kamu Yakin Ingin Menghapus Item Toko Ini?")
                            .setPositiveButton("Ya") { dialog, _ ->
                                // delete data menggunakan Room Database
                                val productDao = MyDatabase.getDatabase(c).productDao()
                                GlobalScope.launch {
                                    productDao.deleteItem(position)

                                    withContext(Dispatchers.Main) {
                                        //perlu digaris bawahi khususnya saya sendiri 'userList' harus berupa arrayList agar dapat memanggil sebuah removeAt terimakasih
                                        userList.removeAt(adapterPosition)
                                        notifyItemRemoved(adapterPosition)
                                        notifyDataSetChanged()
                                        Toast.makeText(
                                            c,
                                            "Informasi Terhapus",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                        dialog.dismiss()
                                    }
                                }
                            }
                            .setNegativeButton("Tidak") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        true
                    }

                    else -> true
                }
            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu, true)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    fun setData(dataProduct: ArrayList<ProductData>) {
        val productDiffUtil = ProductDiffCallbackNew(userList, dataProduct)
        val productDiffUtilResult = DiffUtil.calculateDiff(productDiffUtil)
        this.userList = dataProduct
        productDiffUtilResult.dispatchUpdatesTo(this)
    }
}