package com.example.konsinyasiapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.konsinyasiapp.Converter
import com.example.konsinyasiapp.adapter.DepositAdapter
import com.example.konsinyasiapp.databinding.FragmentHomeBinding
import com.example.konsinyasiapp.viewModel.DepositViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val mDepositViewModel: DepositViewModel by viewModels()
    private var depositAdapter: DepositAdapter = DepositAdapter {

    }

    private val totalAmountTextView: TextView
        get() = binding.tvJumlahHargaYangTerjualHome

    private val converter: Converter by lazy {
        Converter(totalAmountTextView, null)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        mDepositViewModel.getAllUnfinishedDeposit().observe(viewLifecycleOwner) { depositList ->
            mDepositViewModel.checkDatabaseEmpty(depositList)
            depositAdapter.setData(depositList)
        }
        mDepositViewModel.checkDatabaseEmptyLiveData().observe(viewLifecycleOwner, Observer {
            showEmptyDatabaseViews(it)
        })
        mDepositViewModel.getAllDepositHome().observe(viewLifecycleOwner) { deposit ->
            mDepositViewModel.getAllDepositProduct().observe(viewLifecycleOwner) { depositProduct ->
                mDepositViewModel.getProductDepositsForCurrentMonth(deposit, depositProduct)
                mDepositViewModel.getAllFilteredInDeposit(deposit, depositProduct)
                mDepositViewModel.getAllFilterdDepositLiveData()
                    .observe(viewLifecycleOwner) { listProductInDepositFiltered ->
                        mDepositViewModel.calculateTotalAmountLiveData(listProductInDepositFiltered)
                    }
                mDepositViewModel.calculateTotalAmountLiveData()
                    .observe(viewLifecycleOwner) { totalAmount ->
                        binding.tvJumlahHargaYangTerjualHome.text =
                            (converter.formatRupiah(totalAmount.toString()))
                    }
            }
        }
    }

    private fun showEmptyDatabaseViews(emptyDatabase: Boolean) {
        if (emptyDatabase) {
            binding.noDataTextView.visibility = View.VISIBLE
        } else {
            binding.noDataTextView.visibility = View.INVISIBLE
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvDepositHome
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this@HomeFragment.depositAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}