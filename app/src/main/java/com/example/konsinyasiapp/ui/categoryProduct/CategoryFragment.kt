package com.example.konsinyasiapp.ui.categoryProduct

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.konsinyasiapp.databinding.FragmentCategoryBinding
import com.example.konsinyasiapp.adapter.CategoryAdapter
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
    //private lateinit var adapter: CategoryAdapter

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
        })

        val adapter = CategoryAdapter { deletedItem, categoryData ->
            showDeleteAlertDialog(deletedItem, categoryData)
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvCategory
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)

    }

    private fun showDeleteAlertDialog(deletedItem: CategoryData, categoryData: CategoryData) {

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Hapus Title")
        alertDialogBuilder.setMessage("Anda yakin ingin menghapus ini ${deletedItem.nameCategory}?")
        alertDialogBuilder.setPositiveButton("Hapus") { _, _ ->
            mCategoryViewModel.deleteItem(categoryData)
            Snackbar.make(
                requireView(),
                "Berhasil Menghapus Item",
                Snackbar.LENGTH_SHORT
            ).show()
        }
        alertDialogBuilder.setNegativeButton("Batal", null)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}