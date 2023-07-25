package com.example.konsinyasiapp.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.database.MyDatabase
import com.example.konsinyasiapp.databinding.EditShopBinding
import com.example.konsinyasiapp.databinding.ItemShopBinding
import com.example.konsinyasiapp.entities.ShopData
import com.example.konsinyasiapp.ui.shop.ShopFragmentDirections
import com.example.konsinyasiapp.utils.ShopDiffCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShopAdapter(val c: Context, var userList: ArrayList<ShopData>) : RecyclerView.Adapter<ShopAdapter.MyViewHolder>() {


    inner class MyViewHolder(private val binding: ItemShopBinding) :
        RecyclerView.ViewHolder(binding.root) {



        fun bind(shopData: ShopData) {
            binding.shopData = shopData
            binding.executePendingBindings()
            binding.mMenus.setOnClickListener {
                popupMenus(it)
            }
            itemView.setOnClickListener { view ->
                val action = ShopFragmentDirections.actionNavShopToActionNavShopDetail(shopData)
                view.findNavController().navigate(action)
            }
        }

        @SuppressLint("InflateParams", "NotifyDataSetChanged")
        private fun popupMenus(v: View) {
            val position = userList[adapterPosition]
            val popupMenus = PopupMenu(c, v)
            popupMenus.inflate(R.menu.shop_fragment_menu_more)
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.edit_text -> {
                        val binding = EditShopBinding.inflate(LayoutInflater.from(c))
                        val name = binding.edtNamaTokoEdit
                        val address = binding.edtAlamatEdit
                        val ownerShop = binding.edtNamaPemilikEdit
                        val phoneNumber = binding.edtNomorTeleponEdit

                        // mengeset data akan muncul di edit text
                        name.setText(position.name)
                        address.setText(position.address)
                        ownerShop.setText(position.ownerName)
                        phoneNumber.setText(position.phoneNumber)

                        val dialogView = binding.root

                        val dialogBuilder = AlertDialog.Builder(c)
                            .setTitle("Edit Toko")
                            .setView(dialogView)
                            .setPositiveButton("Ok", null)
                            .setNegativeButton("Cancel", null)

                        val dialog = dialogBuilder.create()
                        dialog.setOnShowListener {
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                                position.name = name.text.toString()
                                position.address = address.text.toString()
                                position.ownerName = ownerShop.text.toString()
                                position.phoneNumber = phoneNumber.text.toString()

                                // Update data menggunakan Room Database
                                val shopDao = MyDatabase.getDatabase(c).shopDao()
                                GlobalScope.launch {
                                    shopDao.updateData(position)

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
                            .setMessage("Apa Kamu Yakin Ingin Menghapus Informasi Ini?")
                            .setPositiveButton("Ya") { dialog, _ ->
                                // delete data menggunakan Room Database
                                val shopDao = MyDatabase.getDatabase(c).shopDao()
                                GlobalScope.launch {
                                    shopDao.deleteItem(position)

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
                ItemShopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MyViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return userList.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val currentItem = userList[position]
            holder.bind(currentItem)
        }

        fun setData(shopdata: ArrayList<ShopData>) {
            val shopDiffUtil = ShopDiffCallback(userList, shopdata)
            val shopDiffUtilResult = DiffUtil.calculateDiff(shopDiffUtil)
            this.userList = shopdata
            shopDiffUtilResult.dispatchUpdatesTo(this)
        }
    }