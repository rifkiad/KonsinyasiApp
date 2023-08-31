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
import com.example.konsinyasiapp.entities.ProductWithCategory
import com.example.konsinyasiapp.viewModel.ProductViewModel
import com.google.android.material.snackbar.Snackbar

class FragmentProduct : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private val mProductViewModel: ProductViewModel by viewModels()
    private val productAdapter by lazy {
        ProductAdapter(
            onItemClick = { productWithCategory -> showEditDialog(productWithCategory) },
            onDeleteClick = { productWithCategory -> deleteProduct(productWithCategory) }
        )
    }

//    private lateinit var productAdapterNew: ProductAdapterNew
//    private var list = arrayListOf<ProductData>()

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
        setupRecyclerView()
        observeLiveData()
        setupMenuProvider()
    }

    private fun observeLiveData() {
        mProductViewModel.getAllProductsWithCategories()
            .observe(viewLifecycleOwner) { data ->
                productAdapter.setData(data)
                mProductViewModel.checkDatabaseEmpty(data)
            }

        mProductViewModel.checkDatabaseEmptyLiveData().observe(viewLifecycleOwner, Observer {
            showEmptyDatabaseViews(it)
        })
    }

    private fun setupMenuProvider() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.shop_fragment_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.menu_delete_all -> confirmRemoval()
                }
                return NavigationUI.onNavDestinationSelected(menuItem, view!!.findNavController())
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun showEmptyDatabaseViews(emptyDatabase: Boolean) {
        binding.noDataImageView.visibility = if (emptyDatabase) View.VISIBLE else View.INVISIBLE
        binding.noDataTextView.visibility = if (emptyDatabase) View.VISIBLE else View.INVISIBLE
    }

    private fun setupRecyclerView() {
        binding.rvProduct.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@FragmentProduct.productAdapter
        }
    }

    private fun showEditDialog(product: ProductWithCategory) {
        val dialogBinding = EditProductBinding.inflate(LayoutInflater.from(requireContext()))

        dialogBinding.edtNamaProdukEdit.setText(product.productData.namaProduct)
        dialogBinding.edtHargaProdukEdit.setText(product.productData.hargaProduct)
        dialogBinding.autoCompleteTextViewCategoryEdit.setText(product.productData.categoryId.toString())

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Edit Produk")
            .setView(dialogBinding.root)
            .setPositiveButton("Simpan") { _, _ ->
                val editedName = dialogBinding.edtNamaProdukEdit.text.toString()
                val editedCategory =
                    dialogBinding.autoCompleteTextViewCategoryEdit.text.toString().toInt()
                val editedPrice = dialogBinding.edtHargaProdukEdit.text.toString()

                if (editedName.isNotEmpty()) {
                    val updatedProduct = product.productData.copy(
                        namaProduct = editedName,
                        categoryId = editedCategory,
                        hargaProduct = editedPrice
                    )
                    mProductViewModel.updateData(updatedProduct)

                    showSnackbar( "Produk Berhasil Di Edit!", 800)
                }
            }
            .setNegativeButton("Batal", null)
            .create()

        dialog.show()
    }

    private fun deleteProduct(product: ProductWithCategory) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Ya") { _, _ ->
            mProductViewModel.deleteItem(product.productData)
            showSnackbar( "Produk Berhasil Di Hapus!", 800)
        }
        builder.setNegativeButton("Tidak", null)
        builder.setTitle("Hapus Produk")
        builder.setMessage("Kamu Yakin Ingin Menghapus Item Produk Ini?")
        builder.create().show()
    }

    private fun confirmRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Ya") { _, _ ->
            mProductViewModel.deleteAll()
            productAdapter.setData(emptyList())
            showSnackbar("Berhasil Semua Item", 800)
        }
        builder.setNegativeButton("Tidak") { _, _ -> }
        builder.setTitle("Hapus semua item?")
        builder.setMessage("Anda akan menghapus seluruh isi. Lanjutkan? ")
        builder.create().show()
    }

    private fun showSnackbar(message: String, duration: Int) {
        val snackbar = Snackbar.make(requireView(), message, duration)
        snackbar.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
