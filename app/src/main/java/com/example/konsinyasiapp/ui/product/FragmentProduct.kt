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
import com.example.konsinyasiapp.databinding.EditProductBinding
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
            data = dataProduct
        }
    }

    var data: ProductData? = null

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
                menuInflater.inflate(R.menu.shop_fragment_menu_more, menu)

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.menu_delete_all -> confirmRemoval()
                    R.id.edit_text -> data?.let { editText(it) }
                    R.id.delete -> deleteProduct()

                }
                return NavigationUI.onNavDestinationSelected(menuItem, view.findNavController())
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvProduct
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this@FragmentProduct.productAdapter
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

    private fun deleteProduct() {

    }

    private fun editText(productData: ProductData) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Ya") { _, _ ->
            mProductViewModel.updateData(productData)
            // Panggil fungsi untuk menampilkan dialog edit produk
            //showDialogEditProduct(dataProduct)
        }
        builder.setNegativeButton("Tidak", null)
        builder.setTitle("Edit Item?")
        builder.setMessage("Anda akan mengedit item. Lanjutkan? ")
        builder.create().show()
    }

    private fun showAlertDialog(data: ProductData) {
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

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
