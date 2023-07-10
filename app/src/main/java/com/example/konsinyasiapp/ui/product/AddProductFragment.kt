package com.example.konsinyasiapp.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.databinding.FragmentAddProductBinding
import com.example.konsinyasiapp.databinding.FragmentAddShopBinding


class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_product, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}