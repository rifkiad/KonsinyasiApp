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

class ShopAdapter(
    val context: Context,
    var userList: ArrayList<ShopData>
) : RecyclerView.Adapter<ShopAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: ItemShopBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            setupClickListeners()
        }

        fun bind(shopData: ShopData) {
            binding.shopData = shopData
            binding.executePendingBindings()
        }

        private fun setupClickListeners() {
            binding.mMenus.setOnClickListener { popupMenus(it) }
            itemView.setOnClickListener {
                val action = ShopFragmentDirections.actionNavShopToActionNavShopDetail(
                    userList[adapterPosition]
                )
                it.findNavController().navigate(action)
            }
        }

        @SuppressLint("DiscouragedPrivateApi")
        private fun popupMenus(view: View) {
            val position = userList[adapterPosition]
            val popupMenus = PopupMenu(context, view)
            popupMenus.inflate(R.menu.shop_fragment_menu_more)
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.edit_text -> {
                        showEditDialog(position)
                        true
                    }

                    R.id.delete -> {
                        showDeleteDialog(position)
                        true
                    }

                    else -> false
                }
            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu, true)
        }

        private fun showEditDialog(shopData: ShopData) {
            val dialogBinding = EditShopBinding.inflate(LayoutInflater.from(context))
            dialogBinding.edtNamaTokoEdit.setText(shopData.name)
            dialogBinding.edtAlamatEdit.setText(shopData.address)
            dialogBinding.edtNamaPemilikEdit.setText(shopData.ownerName)
            dialogBinding.edtNomorTeleponEdit.setText(shopData.phoneNumber)

            val dialogView = dialogBinding.root

            val dialogBuilder = AlertDialog.Builder(context)
                .setTitle("Edit Toko")
                .setView(dialogView)
                .setPositiveButton("Ok", null)
                .setNegativeButton("Cancel", null)

            val dialog = dialogBuilder.create()
            dialog.setOnShowListener {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                    val newName = dialogBinding.edtNamaTokoEdit.text.toString()
                    val newAddress = dialogBinding.edtAlamatEdit.text.toString()
                    val newOwnerName = dialogBinding.edtNamaPemilikEdit.text.toString()
                    val newPhoneNumber = dialogBinding.edtNomorTeleponEdit.text.toString()

                    if (newName.isNotEmpty()) {
                        val updatedShopData = shopData.copy(
                            name = newName,
                            address = newAddress,
                            ownerName = newOwnerName,
                            phoneNumber = newPhoneNumber
                        )

                        val shopDao = MyDatabase.getDatabase(context).shopDao()
                        GlobalScope.launch {
                            shopDao.updateData(updatedShopData)

                            withContext(Dispatchers.Main) {
                                notifyDataSetChanged()
                                Toast.makeText(
                                    context,
                                    "Informasi Telah TerUpdate",
                                    Toast.LENGTH_SHORT
                                ).show()
                                dialog.dismiss()
                            }
                        }
                    }
                }
            }

            dialog.show()
        }

        private fun showDeleteDialog(shopData: ShopData) {
            AlertDialog.Builder(context)
                .setTitle("Hapus")
                .setIcon(R.drawable.ic_warning)
                .setMessage("Apa Kamu Yakin Ingin Menghapus Item Toko Ini?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val shopDao = MyDatabase.getDatabase(context).shopDao()
                    GlobalScope.launch {
                        shopDao.deleteItem(shopData)

                        withContext(Dispatchers.Main) {
                            userList.remove(shopData)
                            notifyItemRemoved(adapterPosition)
                            notifyDataSetChanged()
                            Toast.makeText(
                                context,
                                "Informasi Terhapus",
                                Toast.LENGTH_SHORT
                            ).show()
                            dialog.dismiss()
                        }
                    }
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemShopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.bind(currentItem)
    }

    fun setData(newList: ArrayList<ShopData>) {
        val shopDiffUtil = ShopDiffCallback(userList, newList)
        val shopDiffResult = DiffUtil.calculateDiff(shopDiffUtil)
        userList = newList
        shopDiffResult.dispatchUpdatesTo(this)
    }
}
