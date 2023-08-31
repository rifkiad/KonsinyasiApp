package com.example.konsinyasiapp.ui.deposit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.adapter.RincianDepositAdapter
import com.example.konsinyasiapp.databinding.FragmentRincianDepositBinding
import com.example.konsinyasiapp.entities.StatusDeposit
import com.example.konsinyasiapp.ui.home.HomeFragment
import com.example.konsinyasiapp.viewModel.DepositViewModel
import com.example.konsinyasiapp.viewModel.ProductInDepositViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RincianDepositFragment : Fragment() {

    private var _binding: FragmentRincianDepositBinding? = null
    private val binding get() = _binding!!

    private val args: RincianDepositFragmentArgs by navArgs()

    private val mProductInDeposit: ProductInDepositViewModel by viewModels()
    private val mDepositViewModel: DepositViewModel by viewModels()

    private lateinit var rincianDepositAdapter: RincianDepositAdapter
    private var totalSoldProduct: Long = 0L

    private var state = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRincianDepositBinding.inflate(inflater, container, false)
        binding.data = args
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        state = args.state
        totalSoldProduct = args.soldProduct
        rincianDepositAdapter = RincianDepositAdapter(emptyList())
        setupRecyclerView()
        val idDeposit = args.currentItem.depositData.id

        mProductInDeposit.filterProduct(idDeposit).observe(viewLifecycleOwner) { data ->
            rincianDepositAdapter.setData(data)
            val newTotalSoldProduct = data.sumOf { it.productInDeposit.soldProduct }
            totalSoldProduct = newTotalSoldProduct
            val depositWithShop = args.currentItem
            binding.tvNamaTokoRincianDeposit.text = depositWithShop.shopData?.name
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupListeners() {
        binding.btnRincianDeposit.setOnClickListener {
            val depositData = args.currentItem.depositData
            val formattedDate = Date().formatDate("EEEE, dd-MM-yyyy", Locale("id", "ID"))
            viewLifecycleOwner.lifecycleScope.launch {
                mDepositViewModel.finishDeposit(depositData)
                mDepositViewModel.updateDepositStatus(depositData.id, StatusDeposit.SELESAI)
                rincianDepositAdapter.notifyDataSetChanged()

                if (state) {
                    findNavController().navigate(R.id.action_rincian_deposit_to_nav_home)
                } else {
                    findNavController().navigate(R.id.action_rincian_deposit_to_nav_deposit)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvProductInDepositRincian
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this@RincianDepositFragment.rincianDepositAdapter
    }

    private fun Date.formatDate(format: String, locale: Locale): String {
        val dateFormat = SimpleDateFormat(format, locale)
        return dateFormat.format(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
