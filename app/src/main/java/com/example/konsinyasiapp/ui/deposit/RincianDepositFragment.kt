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
import com.example.konsinyasiapp.viewModel.ProductInDepositViewModel

class RincianDepositFragment : Fragment() {

    private var _binding: FragmentRincianDepositBinding? = null
    private val binding get() = _binding!!

    private val args: RincianDepositFragmentArgs by navArgs()
    private val mProductInDeposit: ProductInDepositViewModel by viewModels()

    private var idDeposit = 0L
    private lateinit var depositDetailAdapter: DepositDetailAdapter


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

        //setupRecyclerView
        setupRecyclerView()
        args.currentItem.let { depositWithShop ->
            depositWithShop.let {
                idDeposit = it.depositData.id
            }
        }

//        val idDeposit = args.idDeposit
        mProductInDeposit.filterProduct(idDeposit).observe(viewLifecycleOwner) { data ->
            depositDetailAdapter.setData(data)
        }

        // Inisialisasi navController
        val navController = findNavController()

        val deposit = arguments?.getParcelable<DepositWithShop>("deposit")

        binding.tvNamaTokoRincianDeposit.text = deposit?.shopData?.name

    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvProductInDepositRincian
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this@RincianDepositFragment.depositDetailAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}