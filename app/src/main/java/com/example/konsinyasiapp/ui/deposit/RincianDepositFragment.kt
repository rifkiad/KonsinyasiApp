package com.example.konsinyasiapp.ui.deposit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.konsinyasiapp.adapter.DepositDetailAdapter
import com.example.konsinyasiapp.adapter.RincianDepositAdapter
import com.example.konsinyasiapp.databinding.FragmentRincianDepositBinding
import com.example.konsinyasiapp.entities.DepositWithShop
import com.example.konsinyasiapp.entities.ProductInDeposit
import com.example.konsinyasiapp.viewModel.ProductInDepositViewModel

class RincianDepositFragment : Fragment() {

    private var _binding: FragmentRincianDepositBinding? = null
    private val binding get() = _binding!!

    private val args: RincianDepositFragmentArgs by navArgs()
    private val mProductInDeposit: ProductInDepositViewModel by viewModels()

    private lateinit var rincianDepositAdapter: RincianDepositAdapter

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

        rincianDepositAdapter = RincianDepositAdapter(emptyList())

        //setupRecyclerView
        setupRecyclerView()
        val idDeposit = args.currentItem.depositData.id

        mProductInDeposit.filterProduct(idDeposit).observe(viewLifecycleOwner) { data ->
            rincianDepositAdapter.setData(data)
        }

        // Set nama toko di tampilan
        val depositWithShop = args.currentItem
        binding.tvNamaTokoRincianDeposit.text = depositWithShop.shopData?.name
    }


    private fun setupRecyclerView() {
        val recyclerView = binding.rvProductInDepositRincian
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this@RincianDepositFragment.rincianDepositAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}