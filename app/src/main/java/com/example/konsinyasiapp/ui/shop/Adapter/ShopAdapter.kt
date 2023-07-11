package com.example.konsinyasiapp.ui.shop.Adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.data.ShopData
import com.example.konsinyasiapp.databinding.EditShopBinding
import com.example.konsinyasiapp.databinding.ItemShopBinding

class ShopAdapter(val c: Context, var userList: List<ShopData>) :
    RecyclerView.Adapter<ShopAdapter.MyViewHolder>() {


    inner class MyViewHolder(private val binding: ItemShopBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(shopData: ShopData) {
            binding.shopData = shopData
            binding.executePendingBindings()
            binding.mMenus.setOnClickListener {
                popupMenus(it)
            }
        }

        @SuppressLint("InflateParams")
        private fun popupMenus(v: View) {
            val position = userList[adapterPosition]
            val popupMenus = PopupMenu(c, v)
            popupMenus.inflate(R.menu.shop_fragment_menu)
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.edit_text -> {
                        val binding = EditShopBinding.inflate(LayoutInflater.from(c))
                        val name = binding.edtNamaTokoEdit
                        val address = binding.edtAlamatEdit
                        val ownerShop = binding.edtNamaPemilikEdit
                        val phoneNumber = binding.edtNomorTeleponEdit

                        val dialogView = binding.root

                        val dialogBuilder = AlertDialog.Builder(c)
                            .setTitle("Edit Shop")
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
                                notifyDataSetChanged()
                                Toast.makeText(
                                    c,
                                    "User Information is Edited",
                                    Toast.LENGTH_SHORT
                                ).show()
                                dialog.dismiss()
                            }
                        }

                        dialog.show()

                        true
                    }

                    R.id.delete -> {
                        AlertDialog.Builder(c)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_warning)
                            .setMessage("Are you sure you want to delete this information?")
                            .setPositiveButton("Yes") { dialog, _ ->
                                //this motherFucker
                                //userList.removeAt(adapterPosition)
                                notifyItemRemoved(adapterPosition)
                                Toast.makeText(c, "Deleted this Information", Toast.LENGTH_SHORT)
                                    .show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No") { dialog, _ ->
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

    fun setData(data: List<ShopData>) {
        val toDoDiffUtil = ShopDiffCallback(userList, data)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.userList = data
        toDoDiffResult.dispatchUpdatesTo(this)
    }
}