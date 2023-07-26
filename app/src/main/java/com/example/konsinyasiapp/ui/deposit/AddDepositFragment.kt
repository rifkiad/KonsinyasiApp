package com.example.konsinyasiapp.ui.deposit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.konsinyasiapp.DatePickerFragment
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.databinding.FragmentAddDepositBinding
import com.example.konsinyasiapp.entities.ShopData
import com.example.konsinyasiapp.viewModel.DepositViewModel
import com.example.konsinyasiapp.viewModel.SharedViewModel
import com.example.konsinyasiapp.viewModel.ShopViewModel
=======
import androidx.fragment.app.Fragment
import com.example.konsinyasiapp.DatePickerFragment
import com.example.konsinyasiapp.databinding.FragmentAddDepositBinding
>>>>>>> origin/master

class AddDepositFragment : Fragment() {
    private var _binding: FragmentAddDepositBinding? = null
    private val binding get() = _binding!!

<<<<<<< HEAD
    private lateinit var autoCompleteTextViewShop: AutoCompleteTextView
    private lateinit var shopAdapter: ArrayAdapter<String>

    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mDepositViewModel: DepositViewModel by viewModels()
    private val shopViewModel: ShopViewModel by viewModels()

    private var shopData = arrayListOf<String>()
    private var listShop = listOf<ShopData>()
    private var shopId = 0
    private var shopName = listOf<ShopData>()


=======
>>>>>>> origin/master
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

        return binding.root

<<<<<<< HEAD
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
        object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedShop = listShop[p2]
                shopId = selectedShop.id
            }
        }
=======

>>>>>>> origin/master
    }
}