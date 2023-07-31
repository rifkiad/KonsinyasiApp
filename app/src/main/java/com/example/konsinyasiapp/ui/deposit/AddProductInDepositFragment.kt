package com.example.konsinyasiapp.ui.deposit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.adapter.ProductInDepositAdapter
import com.example.konsinyasiapp.databinding.FragmentAddProductInDepositBinding
import com.example.konsinyasiapp.entities.DepositData
import com.example.konsinyasiapp.entities.ProductData
import com.example.konsinyasiapp.entities.ProductInDeposit
import com.example.konsinyasiapp.viewModel.DepositViewModel
import com.example.konsinyasiapp.viewModel.ProductInDepositViewModel
import com.example.konsinyasiapp.viewModel.ProductViewModel
import com.example.konsinyasiapp.viewModel.SharedViewModel

class AddProductInDepositFragment : Fragment() {
    private var _binding: FragmentAddProductInDepositBinding? = null
    private val binding get() = _binding!!

    private lateinit var autoCompleteTexViewProduct: AutoCompleteTextView
    private lateinit var productAdapter: ArrayAdapter<String>

    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mProductInDeposit: ProductInDepositViewModel by viewModels()
    private val productViewModel: ProductViewModel by viewModels()
    //private val mDepositViewModel: DepositViewModel by viewModels()


    private var productData = arrayListOf<String>()
    private var listProduct = listOf<ProductData>()
    private var productId = 1
    private var productName = listOf<ProductData>()

    private var productInDepositAdapter: ProductInDepositAdapter = ProductInDepositAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddProductInDepositBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setup RecylerView
        setupRecyclerView()

        //observe liveData
        mProductInDeposit.getAllProductWithDeposit()
            .observe(viewLifecycleOwner) { data ->
                productInDepositAdapter.setData(data)
            }

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

        binding.btnSimpanProduk.setOnClickListener {
            insertDataToProductDeposit()
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvProductInDeposit
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this@AddProductInDepositFragment.productInDepositAdapter
    }

    private fun insertDataToProductDeposit() {
        val mProduct = productId
        val mJumlahQuantity = binding.etJumlahBarang.text.toString()

        val validation = mSharedViewModel.verifyDataFromProductToDeposit(mProduct.toString(), mJumlahQuantity)
        if (validation) {
            //insert to Database
            val newDeposit = ProductInDeposit(
                0,
                mProduct.toLong(),
                mJumlahQuantity
            )
            mProductInDeposit.insertData(newDeposit)
            Toast.makeText(requireContext(), "Product Berhasil Ditambahkan!", Toast.LENGTH_SHORT)
                .show()
            // Navigate back
            findNavController().navigate(R.id.action_addProductInDeposit_to_nav_deposit)
        } else {
            Toast.makeText(requireContext(), "Harap Kolom Diisi", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}