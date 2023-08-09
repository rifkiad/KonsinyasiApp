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

    private lateinit var autoCompleteTexViewProduct: AutoCompleteTextView
    private lateinit var productAdapter: ArrayAdapter<String>
    private var productInDepositAdapter: ProductInDepositAdapter = ProductInDepositAdapter()

    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mProductInDeposit: ProductInDepositViewModel by viewModels()
    private val productViewModel: ProductViewModel by viewModels()
    private val DepositViewModel: DepositViewModel by viewModels()


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
    ): View? {
        _binding = FragmentAddProductInDepositBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idDeposit = args.idDeposit

        //setup RecylerView
        setupRecyclerView()

        // Observer untuk hasil filter produk berdasarkan id deposit
        mProductInDeposit.filterProduct(idDeposit).observe(viewLifecycleOwner) { data ->
            productInDepositAdapter.setData(data)
        }

        //pengundangan productData ke dalam ExposedDropdownMenu
        autoCompleteTexViewProduct = binding.autoCompleteTextViewProduct
        productAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, productData)

        autoCompleteTexViewProduct.setAdapter(productAdapter)

        productViewModel.getAllProduct.observe(viewLifecycleOwner) { products ->
            listProduct = products
            for (product in listProduct) {
                productData.add(product.namaProduct)
            }
        }

        //bagian id dari deposit
        DepositViewModel.gettAllDeposit.observe(viewLifecycleOwner) { deposits ->
            listDeposit = deposits
            for (deposit in listDeposit) {
                depositData.add(deposit.id.toString())
            }
        }

        //mengundang untuk bagian idnya
        binding.autoCompleteTextViewProduct.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, Int, _ ->
                val selectedProduct = listProduct[Int]
                productId = selectedProduct.id
            }

        binding.btnSimpanProduk.setOnClickListener {
            if (!isDataAdded) {
                // pesan bila user belum mengisi data dan belum menambah datanya menggunakan 'tambahProduk'
                Toast.makeText(
                    requireContext(),
                    "Harap tambahkan produk terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                //action selanjutnya bila user sudah mengklik button simpan
                it.findNavController().navigate(R.id.action_addProductInDeposit_to_nav_deposit)
            }
        }

        binding.btnTambahProduk.setOnClickListener {
            insertDataToAddProductDeposit()

            //mengosongkan input datanya bila datanya sudah ditambahkan
            binding.etJumlahBarang.text = null
            binding.autoCompleteTextViewProduct.text = null

            // Set isDataAdded menjadi true setelah data ditambahkan
            isDataAdded = true
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvProductInDeposit
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this@AddProductInDepositFragment.productInDepositAdapter
    }

    private fun insertDataToAddProductDeposit() {
        val mProduct = productId
        val mDeposit = idDeposit
        val mJumlahQuantity = binding.etJumlahBarang.text.toString()

        val validation =
            mSharedViewModel.verifyDataFromProductToDeposit(mProduct.toString(), mJumlahQuantity)
        if (validation) {
            //insert to Database
            val newDeposit = ProductInDeposit(
                0,
                mProduct,
                mDeposit,
                mJumlahQuantity,
                0

            )
            mProductInDeposit.insertDataProductInDeposit(newDeposit)
            Toast.makeText(requireContext(), "Product Berhasil Ditambahkan!", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(requireContext(), "Harap Kolom Diisi", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}