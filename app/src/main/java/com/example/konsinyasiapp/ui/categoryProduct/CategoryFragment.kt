package com.example.konsinyasiapp.ui.categoryProduct

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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.adapter.CategoryAdapter
import com.example.konsinyasiapp.databinding.FragmentCategoryBinding
import com.example.konsinyasiapp.entities.CategoryData
import com.example.konsinyasiapp.viewModel.CategoryViewModel
import com.google.android.material.snackbar.Snackbar


class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    private val adapter: CategoryAdapter by lazy {
        CategoryAdapter { deletedItem, categoryData ->
            showDeleteAlertDialog(deletedItem, categoryData)
        }
    }

    private lateinit var categoryAdapter: CategoryAdapter
    private val mCategoryViewModel: CategoryViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryAdapter = adapter

        //setup recyclerview
        setupRecyclerView()

        //observe livedata
        mCategoryViewModel.getAllCategory.observe(viewLifecycleOwner, Observer { data ->
            adapter.setData(data)
            mCategoryViewModel.checkDatabaseEmpty(data)
        })

        mCategoryViewModel.checkDatabaseEmptyLiveData().observe(viewLifecycleOwner, Observer {
            showEmptyDatabaseViews(it)
        })

        val adapter = CategoryAdapter { deletedItem, categoryData ->
            showDeleteAlertDialog(deletedItem, categoryData)
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
            mCategoryViewModel.deleteAll()
            showSnackbar(
                "Berhasil Semua Item", 800
            )
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Hapus semua item?")
        builder.setMessage("Anda akan menghapus seluruh isi. Lanjutkan?")
        builder.create().show()
    }

    private fun showEmptyDatabaseViews(emptyDatabase: Boolean) {
        if (emptyDatabase) {
            binding.noDataImageView.visibility = View.VISIBLE
            binding.noDataTextView.visibility = View.VISIBLE
        } else {
            binding.noDataImageView.visibility = View.INVISIBLE
            binding.noDataTextView.visibility = View.INVISIBLE
        }
    }


    private fun setupRecyclerView() {
        val recyclerView = binding.rvCategory
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)

    }

    private fun showDeleteAlertDialog(deletedItem: CategoryData, categoryData: CategoryData) {

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Hapus Kategori")
        alertDialogBuilder.setMessage("Anda yakin ingin menghapus ${deletedItem.nameCategory}?")
        alertDialogBuilder.setPositiveButton("Hapus") { _, _ ->
            mCategoryViewModel.deleteItem(categoryData)
            showSnackbar("Berhasil Menghapus Item", 800)
        }
        alertDialogBuilder.setNegativeButton("Batal", null)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun showSnackbar(message: String, duration: Int) {
        val snackbar = Snackbar.make(requireView(), message, duration)
        snackbar.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}