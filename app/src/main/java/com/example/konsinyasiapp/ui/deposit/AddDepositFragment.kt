package com.example.konsinyasiapp.ui.deposit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.konsinyasiapp.DatePickerFragment
import com.example.konsinyasiapp.databinding.FragmentAddDepositBinding

class AddDepositFragment : Fragment() {
    private var _binding: FragmentAddDepositBinding? = null
    private val binding get() = _binding!!

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


    }
}