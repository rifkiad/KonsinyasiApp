package com.example.konsinyasiapp.ui.product

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.example.konsinyasiapp.dao.CategoryDao
import com.example.konsinyasiapp.database.ProductDatabase
import com.example.konsinyasiapp.databinding.FragmentAddProductBinding


class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!

    private lateinit var autoCompleteTextViewCategory: AutoCompleteTextView
    private lateinit var categoryDao: CategoryDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddProductBinding.inflate(inflater, container, false)

        autoCompleteTextViewCategory = binding.autoCompleteTextViewCategory

        val productDatabase = ProductDatabase.getDatabase(requireContext())
        categoryDao = productDatabase.categoryDao()

        // Mendapatkan id_kategori_produk dari Fragment Category (contoh: categoryId)
        val categoryId = arguments?.getInt("idKategoriProduk")

        categoryDao.getAllCategory().observe(viewLifecycleOwner) { categories ->
            val filteredCategories = categories.filter { it.id == categoryId }
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                filteredCategories.map { it.nameCategory }
            )
            autoCompleteTextViewCategory.setAdapter(adapter)
        }
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}