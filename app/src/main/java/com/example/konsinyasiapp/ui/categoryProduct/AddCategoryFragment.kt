package com.example.konsinyasiapp.ui.categoryProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.databinding.FragmentAddCategoryBinding
import com.example.konsinyasiapp.entities.CategoryData
import com.example.konsinyasiapp.viewModel.CategoryViewModel
import com.example.konsinyasiapp.viewModel.SharedViewModel
import com.google.android.material.snackbar.Snackbar

class AddCategoryFragment : Fragment() {

    private var _binding: FragmentAddCategoryBinding? = null
    private val binding get() = _binding!!

    private val mCategoryViewModel: CategoryViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // penggunaan viewBinding
        _binding = FragmentAddCategoryBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTambahCategory.setOnClickListener {
            insertDataToCategory()
        }
    }

    private fun insertDataToCategory() {
        val mName = binding.edtNamaProduk.text.toString()

        val validation = mSharedViewModel.verifyDataFromCategory(mName)
        if (validation) {
            //insert to Database
            val newCategory = CategoryData(
                0,
                mName
            )
            mCategoryViewModel.insertData(newCategory)
            showSnackbar("Category Berhasil Ditambahkan!", 800)
            //navigateBack
            findNavController().navigate(R.id.action_action_navCategory_add_to_nav_kategori_produk)
        } else {
            Snackbar.make(requireView(), "Harap Kolom Diisi", Snackbar.LENGTH_SHORT)
                .show()
        }
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