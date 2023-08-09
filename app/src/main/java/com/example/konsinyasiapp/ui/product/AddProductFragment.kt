package com.example.konsinyasiapp.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.konsinyasiapp.Converter
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.databinding.FragmentAddProductBinding
import com.example.konsinyasiapp.entities.CategoryData
import com.example.konsinyasiapp.entities.ProductData
import com.example.konsinyasiapp.viewModel.CategoryViewModel
import com.example.konsinyasiapp.viewModel.ProductViewModel
import com.example.konsinyasiapp.viewModel.SharedViewModel


class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!

    private lateinit var autoCompleteTextViewCategory: AutoCompleteTextView
    private lateinit var categoryAdapter: ArrayAdapter<String>

    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mProductViewModel: ProductViewModel by viewModels()
    private val categoryViewModel: CategoryViewModel by viewModels()

    private var categoryData = arrayListOf<String>()
    private var listCategory = listOf<CategoryData>()
    private var categoryId = 0
    //private var categoryName = listOf<CategoryData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddProductBinding.inflate(inflater, container, false)

        val edtHargaProduk: EditText = binding.edtHargaProduk
        val converter = Converter(edtHargaProduk)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        autoCompleteTextViewCategory = binding.autoCompleteTextViewCategory
        categoryAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, categoryData)

        autoCompleteTextViewCategory.setAdapter(categoryAdapter)

        categoryViewModel.getAllCategory.observe(viewLifecycleOwner) { categories ->
            listCategory = categories
            for (category in listCategory) {
                categoryData.add(category.nameCategory)
            }
        }

        binding.autoCompleteTextViewCategory.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, p2, _ ->
                val selectedCategory = listCategory[p2]
                categoryId = selectedCategory.id
            }
        //without lambda
//        object : AdapterView.OnItemClickListener {
//            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                val selectedCategory = listCategory[p2]
//                categoryId = selectedCategory.id

        binding.btnTambahProduk.setOnClickListener {
            insertDataToProduct()
        }
    }

    private fun insertDataToProduct() {
        val mCategory = categoryId
        val mNama = binding.edtNamaProduk.text.toString()
        val mHarga = binding.edtHargaProduk.text.toString()

        val validation = mSharedViewModel.verifyDataFromProduct(mCategory.toString(), mNama, mHarga)

        if (validation) {

            //insert to Database
            val newProduct = ProductData(
                0,
                mCategory,
                mNama,
                mHarga
            )
            mProductViewModel.insertData(newProduct)
            Toast.makeText(requireContext(), "Product Berhasil Ditambahkan!", Toast.LENGTH_SHORT)
                .show()
            // Navigate back
            findNavController().navigate(R.id.action_action_navProduct_add_to_nav_myProduct)
        } else {
            Toast.makeText(requireContext(), "Harap Kolom Diisi", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
