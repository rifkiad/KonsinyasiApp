package com.example.konsinyasiapp.ui.deposit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.databinding.FragmentAddProductInDepositBinding
import com.example.konsinyasiapp.entities.ProductData
import com.example.konsinyasiapp.viewModel.DepositViewModel
import com.example.konsinyasiapp.viewModel.ProductViewModel
import com.example.konsinyasiapp.viewModel.SharedViewModel

class AddProductInDepositFragment : Fragment() {
    private var _binding: FragmentAddProductInDepositBinding? = null
    private val binding get() = _binding!!

    private lateinit var autoCompleteTexViewProduct: AutoCompleteTextView
    private lateinit var productAdapter: ArrayAdapter<String>

    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mDepositViewModel: DepositViewModel by viewModels()
    private val productViewModel: ProductViewModel by viewModels()

    private var productData = arrayListOf<String>()
    private var listProduct = listOf<ProductData>()
    private var productId = 0
    private var productName = listOf<ProductData>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddProductInDepositBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        autoCompleteTexViewProduct = binding.autoCompleteTextViewProduct
        productAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, productData)

        autoCompleteTexViewProduct.setAdapter(productAdapter)

        productViewModel.getAllProduct.observe(viewLifecycleOwner) { products ->
            listProduct = products
            for (product in listProduct) {
                productData.add(product.namaProduct)
            }
        }

        binding.autoCompleteTextViewProduct
        AdapterView.OnItemClickListener { _, _, Int, _ ->
            val selectedProduct = listProduct[Int]
            productId = selectedProduct.id
        }

    }

}