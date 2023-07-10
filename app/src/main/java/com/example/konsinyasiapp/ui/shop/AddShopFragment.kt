package com.example.konsinyasiapp.ui.shop

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.SharedViewModel
import com.example.konsinyasiapp.database.ShopData
import com.example.konsinyasiapp.databinding.FragmentAddShopBinding
import com.google.android.material.snackbar.Snackbar


class AddShopFragment : Fragment() {

    private var _binding: FragmentAddShopBinding? = null
    private val binding get() = _binding!!

    private val mShopViewModel: ShopViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()


    lateinit var btnTambahToko: Button

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun insertDataToShop() {
        val mName = binding.edtName.text.toString()
        val mAddress = binding.edtAlamat.text.toString()
        val mProductOwner = binding.edtNamaPemilik.text.toString()
        val mPhoneNumber = binding.edtNomorTelepon.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mName, mAddress, mProductOwner, mPhoneNumber)
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
            Toast.makeText(requireContext(), "Berhasil DItambahkan!", Toast.LENGTH_SHORT).show()
            //navigateBack
            findNavController().navigate(R.id.action_action_navshop_topshop_to_nav_shop)
        } else {
            Snackbar.make(requireView(), "Harap semua kolom diisi!", Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}