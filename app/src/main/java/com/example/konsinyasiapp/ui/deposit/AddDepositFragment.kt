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
import androidx.navigation.fragment.findNavController
import com.example.konsinyasiapp.DatePickerFragment
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.databinding.FragmentAddDepositBinding
import com.example.konsinyasiapp.entities.DepositData
import com.example.konsinyasiapp.entities.ShopData
import com.example.konsinyasiapp.viewModel.DepositViewModel
import com.example.konsinyasiapp.viewModel.SharedViewModel
import com.example.konsinyasiapp.viewModel.ShopViewModel


class AddDepositFragment : Fragment() {
    private var _binding: FragmentAddDepositBinding? = null
    private val binding get() = _binding!!

    private lateinit var autoCompleteTextViewShop: AutoCompleteTextView
    private lateinit var shopAdapter: ArrayAdapter<String>

    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mDepositViewModel: DepositViewModel by viewModels()
    private val shopViewModel: ShopViewModel by viewModels()

    private var shopData = arrayListOf<String>()
    private var listShop = listOf<ShopData>()
    private var shopId = 0L
    private var shopName = listOf<ShopData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddDepositBinding.inflate(inflater, container, false)

        //datePicker
        binding.apply {
            tvDatePickerDeposit.setOnClickListener {
                //membuat contoh untuk DatePickerFragment
                val datePickerFragment = DatePickerFragment()
                datePickerFragment.show(requireActivity().supportFragmentManager, "DatePicker")
            }
        }

        requireActivity().supportFragmentManager.setFragmentResultListener(
            "REQUEST_KEY",
            viewLifecycleOwner
        ) { resultKey, bundle ->
            if (resultKey == "REQUEST_KEY") {
                val date = bundle.getString("SELECTED_DATE")
                binding.tvDatePickerDeposit.text = date
            }
        }

        binding.btnLanjut.setOnClickListener {
            insertDataToDeposit()
        }

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        autoCompleteTextViewShop = binding.autoCompleteTextViewShop
        shopAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, shopData)

        autoCompleteTextViewShop.setAdapter(shopAdapter)

        shopViewModel.getAllData.observe(viewLifecycleOwner) { shops ->
            listShop = shops
            for (shop in listShop) {
                shop.name?.let { shopData.add(it) }
            }
        }

        binding.autoCompleteTextViewShop
        AdapterView.OnItemClickListener { _, _, p3, _ ->
            val selectedShop = listShop[p3]
            shopId = selectedShop.id.toLong()
        }
    }

    private fun insertDataToDeposit() {
        val mShopData = shopId
        val mDate = binding.tvDatePickerDeposit.text.toString()


        val validation = mSharedViewModel.verifyDataFromDeposit(mShopData.toString(), mDate)
        if (validation) {
            //insert to Database
            val newDeposit = DepositData(
                0,
                mShopData,
                0,
                mDate
            )
            mDepositViewModel.insertData(newDeposit)
            Toast.makeText(requireContext(), "Product Berhasil Ditambahkan!", Toast.LENGTH_SHORT)
                .show()
            // Navigate back
            findNavController().navigate(R.id.action_deposit_add_to_addProductInDeposit)
        } else {
            Toast.makeText(requireContext(), "Harap Kolom Diisi", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}