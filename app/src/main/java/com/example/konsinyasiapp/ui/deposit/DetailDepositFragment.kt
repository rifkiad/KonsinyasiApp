package com.example.konsinyasiapp.ui.deposit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.adapter.DepositAdapter
import com.example.konsinyasiapp.databinding.FragmentDetailDepositBinding
import com.example.konsinyasiapp.entities.DepositWithShop

class DetailDepositFragment : Fragment() {

    private var _binding: FragmentDetailDepositBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailDepositFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailDepositBinding.inflate(inflater, container, false)
        binding.args = args

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi navController
        val navController = findNavController()

        val deposit = arguments?.getParcelable<DepositWithShop>("deposit")

        binding.tvNamaTokoDepositDetail.text = deposit?.shopData?.name
        binding.tvTanggalDepositDepositDetail.text = deposit?.depositData?.depositDate
        binding.tvFinishDepositDetail.text = deposit?.depositData?.depositFinish
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}