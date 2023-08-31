package com.example.konsinyasiapp.ui.deposit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
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

    private val sharedViewModel: SharedViewModel by viewModels()
    private val productInDepositViewModel: ProductInDepositViewModel by viewModels()
    private val productViewModel: ProductViewModel by viewModels()
    private val depositViewModel: DepositViewModel by viewModels()

    private lateinit var productAdapter: ArrayAdapter<String>
    private lateinit var autoCompleteTexViewProduct: AutoCompleteTextView
    private var productData = arrayListOf<String>()
    private var listProduct = listOf<ProductData>()
    private var productId = 0L

    private var depositData = arrayListOf<String>()
    private var listDeposit = listOf<DepositData>()
    private var idDeposit = 0L

    private val args by navArgs<AddProductInDepositFragmentArgs>()
    private var isDataAdded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddProductInDepositBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArguments()
        setupUI()
        setupObservers()
        setupListeners()
    }

    private fun initArguments() {
        idDeposit = args.idDeposit
    }

    private fun setupUI() {
        autoCompleteTexViewProduct = binding.autoCompleteTextViewProduct
        productAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, productData)
        autoCompleteTexViewProduct.setAdapter(productAdapter)

        val recyclerView = binding.rvProductInDeposit
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ProductInDepositAdapter()
    }

    private fun setupObservers() {
        productViewModel.getAllProduct.observe(viewLifecycleOwner) { products ->
            listProduct = products
            productData.clear()
            for (product in listProduct) {
                productData.add(product.namaProduct)
            }
            productAdapter.notifyDataSetChanged()
        }

        depositViewModel.getAllDeposit.observe(viewLifecycleOwner) { deposits ->
            listDeposit = deposits
            depositData.clear()
            for (deposit in listDeposit) {
                depositData.add(deposit.id.toString())
            }
        }

        productInDepositViewModel.filterProduct(idDeposit).observe(viewLifecycleOwner) { data ->
            (binding.rvProductInDeposit.adapter as ProductInDepositAdapter).setData(data)
        }
    }

    private fun setupListeners() {
        autoCompleteTexViewProduct.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedProduct = listProduct[position]
                productId = selectedProduct.id
            }

        binding.btnTambahProduk.setOnClickListener {
            insertDataToAddProductDeposit()
            binding.etJumlahBarang.text = null
            autoCompleteTexViewProduct.text = null
            isDataAdded = true
        }

        binding.btnSimpanProduk.setOnClickListener {
            if (!isDataAdded) {
                Toast.makeText(
                    requireContext(),
                    "Harap tambahkan produk terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                navigateToDepositScreen()
            }
        }
    }

    private fun insertDataToAddProductDeposit() {
        val mProduct = productId
        val mDeposit = idDeposit
        val mJumlahQuantity = binding.etJumlahBarang.text.toString()

        val validation =
            sharedViewModel.verifyDataFromProductToDeposit(mProduct.toString(), mJumlahQuantity)
        if (validation) {
            val newDeposit = ProductInDeposit(
                0,
                mProduct,
                mDeposit,
                mJumlahQuantity.toInt(),
                0,
                0
            )
            productInDepositViewModel.insertDataProductInDeposit(newDeposit)
            Toast.makeText(requireContext(), "Product Berhasil Ditambahkan!", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(requireContext(), "Harap Kolom Diisi", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToDepositScreen() {
        view?.findNavController()?.navigate(R.id.action_addProductInDeposit_to_nav_deposit)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
