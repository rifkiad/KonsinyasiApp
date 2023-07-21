package com.example.konsinyasiapp.ui.product

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.adapter.ProductAdapter
import com.example.konsinyasiapp.databinding.FragmentProductBinding
import com.example.konsinyasiapp.entities.ProductData
import com.example.konsinyasiapp.viewModel.ProductViewModel
import com.google.android.material.snackbar.Snackbar


class FragmentProduct : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private val mProductViewModel: ProductViewModel by viewModels()

    private val productAdapter by lazy {
        ProductAdapter { dataProduct ->
            showAlertDialog(dataProduct)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setup RecyclerView
        setupRecyclerView()

        //observe livedata
        mProductViewModel.getAllProductsWithCategories()
            .observe(viewLifecycleOwner) { data ->
                productAdapter.setData(data)
            }

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.shop_fragment_menu, menu)

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.menu_delete_all -> confirmRemoval()
                }
                return NavigationUI.onNavDestinationSelected(menuItem, view.findNavController())
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun confirmRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Ya") { _, _ ->
            mProductViewModel.deleteAll()
            Snackbar.make(
                requireView(),
                "Berhasil Semua Item",
                Snackbar.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("Tidak") { _, _ -> }
        builder.setTitle("Hapus semua item?")
        builder.setMessage("Anda akan menghapus seluruh isi. Lanjutkan? ")
        builder.create().show()
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvProduct
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this@FragmentProduct.productAdapter
    }

    private fun showAlertDialog(dataProduct: ProductData) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

//@SuppressLint("DiscouragedPrivateApi")
//private fun showPopupMenu(v: View) {
//    val position = dataProduct[adapterPosition]
//    val popupMenu = PopupMenu(c, v)
//    popupMenu.inflate(R.menu.shop_fragment_menu_more)
//    popupMenu.setOnMenuItemClickListener {
//        when (it.itemId) {
//            R.id.edit_text -> {
//                val binding = EditProductBinding.inflate(LayoutInflater.from(c))
//                val name = binding.edtNamaProdukEdit
//                val categoryItem = binding.autoCompleteTextViewCategoryEdit
//                val price = binding.edtHargaProdukEdit
//
//                // mengeset data akan muncul di edit text
//                name.setText(position.productData.namaProduct)
//                categoryItem.setText(position.categoryData.nameCategory)
//                price.setText(position.productData.hargaProduct)
//
//                val dialogView = binding.root
//
//                val dialogBuilder = AlertDialog.Builder(c)
//                    .setTitle("Edit Product")
//                    .setView(dialogView)
//                    .setPositiveButton("Ok", null)
//                    .setNegativeButton("Cancel", null)
//
//                val dialog = dialogBuilder.create()
//                dialog.setOnShowListener {
//                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
//                        position.productData.namaProduct = name.text.toString()
//                        position.categoryData.nameCategory = categoryItem.toString()
//                        position.productData.hargaProduct = price.text.toString()
//
//                        // Update data menggunakan Room Database
//                        val productDao = ProductDatabase.getDatabase(c).productDao()
//                        GlobalScope.launch {
//                            productDao.updateData(position)
//
//                            withContext(Dispatchers.Main) {
//                                notifyDataSetChanged()
//                                Toast.makeText(
//                                    c,
//                                    "Informasi Telah TerUpdate",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                dialog.dismiss()
//                            }
//                        }
//                    }
//                }
//                dialog.show()
//
//                true
//            }
//
//            R.id.delete -> {
//                AlertDialog.Builder(c)
//                    .setTitle("Hapus")
//                    .setIcon(R.drawable.ic_warning)
//                    .setMessage("Apa Kamu Yakin Ingin Menghapus Informasi Ini?")
//                    .setPositiveButton("Ya") { dialog, _ ->
//                        // delete data menggunakan Room Database
//                        val productDao = ProductDatabase.getDatabase(c).productDao()
//                        GlobalScope.launch {
//                            productDao.deleteItem(position)
//
//                            withContext(Dispatchers.Main) {
//                                //perlu digaris bawahi khususnya saya sendiri 'dataProduct' harus berupa arrayList agar dapat memanggil sebuah removeAt terimakasih
//                                dataProduct.removeAt(adapterPosition)
//                                notifyItemRemoved(adapterPosition)
//                                notifyDataSetChanged()
//                                Toast.makeText(
//                                    c,
//                                    "Informasi Terhapus",
//                                    Toast.LENGTH_SHORT
//                                )
//                                    .show()
//                                dialog.dismiss()
//                            }
//                        }
//                    }
//                    .setNegativeButton("Tidak") { dialog, _ ->
//                        dialog.dismiss()
//                    }
//                    .create()
//                    .show()
//
//                true
//            }
//
//            else -> true
//        }
//    }
//    popupMenu.show()
//    val popup = PopupMenu::class.java.getDeclaredField("mPopup")
//    popup.isAccessible = true
//    val menu = popup.get(popupMenu)
//    menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
//        .invoke(menu, true)
//}