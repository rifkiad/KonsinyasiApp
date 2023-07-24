package com.example.konsinyasiapp.ui.product

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
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
        ProductAdapter { dataProduct, mMenus ->
            showMorePopupMenu(dataProduct, mMenus)
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
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.menu_delete_all -> confirmRemoval()

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

    private fun showMorePopupMenu(productData: ProductData, mMenus: ImageView) {
        val popupMenu = PopupMenu(requireContext(), mMenus)
        popupMenu.menuInflater.inflate(R.menu.shop_fragment_menu_more, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.edit_text -> {
                    showEditDialog(productData)
                    true
                }

                R.id.delete -> {
                    showDeleteConfirmationDialog(productData)
                    true
                }

                else -> false
            }
        }

        popupMenu.show()
    }

    private fun showEditDialog(productData: ProductData) {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.edit_product, null)
        val builder = AlertDialog.Builder(requireContext())
            .setTitle("Edit Produk")
            .setView(dialogView)

        builder.setPositiveButton("Ya") { _, _ ->
            mProductViewModel.updateData(productData)
            Snackbar.make(
                requireView(),
                "Berhasil Mengedit Item",
                Snackbar.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("Tidak", null)

        val alertDialog = builder.create()
        alertDialog.show()

    }

    private fun showDeleteConfirmationDialog(productData: ProductData) {
        val builder = AlertDialog.Builder(requireContext())
            .setTitle("Hapus Produk")
            .setMessage("Anda akan menghapus item ini. Lanjutkan? ")

        builder.setPositiveButton("Ya") { _, _ ->
            // Perform delete operation here with productData
            mProductViewModel.deleteItem(productData)
            Snackbar.make(
                requireView(),
                "Berhasil Menghapus Item",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        builder.setNegativeButton("Tidak", null)

        val alertDialog = builder.create()
        alertDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
