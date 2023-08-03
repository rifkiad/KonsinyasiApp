package com.example.konsinyasiapp.ui.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.konsinyasiapp.entities.ShopData
import com.example.konsinyasiapp.databinding.FragmentDetailShopBinding

class DetailShopFragment : Fragment() {

    private val args by navArgs<DetailShopFragmentArgs>()

    private var _binding: FragmentDetailShopBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailShopBinding.inflate(inflater, container, false)
        binding.args = args

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shop = arguments?.getParcelable<ShopData>("shop")

        // Mengisi data ke tampilan detail
        binding.tvShopNameDetail.text = shop?.name
        binding.tvShopAddress.text = shop?.address
        binding.tvOwnerName.text = shop?.ownerName
        binding.tvPhoneNumber.text = shop?.phoneNumber
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}