package com.example.konsinyasiapp.ui.deposit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.konsinyasiapp.adapter.RincianDepositAdapter
import com.example.konsinyasiapp.databinding.FragmentRincianDepositBinding
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
    private val mDeposit: DepositViewModel by viewModels()

    private lateinit var rincianDepositAdapter: RincianDepositAdapter
    private var totalSoldProduct: Long = 0L

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

        // Dapatkan nilai soldProduct dari args
        totalSoldProduct = args.soldProduct

        // Inisialisasi adapter untuk daftar produk dalam deposit
        rincianDepositAdapter = RincianDepositAdapter(emptyList())
        setupRecyclerView()
        val idDeposit = args.currentItem.depositData.id

        mProductInDeposit.filterProduct(idDeposit).observe(viewLifecycleOwner) { data ->
            rincianDepositAdapter.setData(data)

            // Hitung total jumlah produk yang terjual
            val newTotalSoldProduct = data.sumOf { it.productInDeposit.soldProduct }
            totalSoldProduct = newTotalSoldProduct

            // Set nama toko pada tampilan
            val depositWithShop = args.currentItem
            binding.tvNamaTokoRincianDeposit.text = depositWithShop.shopData?.name
        }
    }

    private fun setupListeners() {
        binding.btnRincianDeposit.setOnClickListener {
            val depositData = args.currentItem.depositData

            // Dapatkan tanggal saat ini dalam format yang diinginkan
            val formattedDate = Date().formatDate("EEEE, dd-MM-yyyy", Locale("id", "ID"))

            // Memanggil finishDeposit dari dalam coroutine
            viewLifecycleOwner.lifecycleScope.launch {
                mDeposit.finishDeposit(depositData)

                // Perbarui data soldProduct di ViewModel
                mProductInDeposit.updateSoldProduct(depositData.id, totalSoldProduct.toInt())

                // Navigasi kembali ke DepositFragment
                val action = RincianDepositFragmentDirections.actionRincianDepositToNavDeposit()
                findNavController().navigate(action)
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