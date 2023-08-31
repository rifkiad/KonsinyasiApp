package com.example.konsinyasiapp.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.databinding.FragmentAddShopBinding
import com.example.konsinyasiapp.entities.ShopData
import com.example.konsinyasiapp.viewModel.SharedViewModel
import com.example.konsinyasiapp.viewModel.ShopViewModel
import com.google.android.material.snackbar.Snackbar


class AddShopFragment : Fragment() {

    private var _binding: FragmentAddShopBinding? = null
    private val binding get() = _binding!!

    private val mShopViewModel: ShopViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // penggunaan viewBinding
        _binding = FragmentAddShopBinding.inflate(inflater, container, false)

        binding.btnTambahToko.setOnClickListener {
            insertDataToShop()
        }
        return binding.root
    }

    private fun insertDataToShop() {
        val mName = binding.edtNamaToko.text.toString()
        val mAddress = binding.edtAlamat.text.toString()
        val mProductOwner = binding.edtNamaPemilik.text.toString()
        val mPhoneNumber = binding.edtNomorTelepon.text.toString()

        val validation =
            mSharedViewModel.verifyDataFromUser(mName, mAddress, mProductOwner, mPhoneNumber)
        if (validation) {
            //insert to Database
            val newData = ShopData(
                0,
                mName,
                mAddress,
                mProductOwner,
                mPhoneNumber
            )
            mShopViewModel.insertData(newData)
            showSnackbar("Berhasil Ditambahkan!", 800)
            //navigateBack
            findNavController().navigate(R.id.action_action_navshop_topshop_to_nav_shop)
        } else {
            showSnackbar("Harap semua kolom diisi!", 800)

        }
    }

    private fun showSnackbar(message: String, duration: Int) {
        val snackbar = Snackbar.make(requireView(), message, duration)
        snackbar.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}