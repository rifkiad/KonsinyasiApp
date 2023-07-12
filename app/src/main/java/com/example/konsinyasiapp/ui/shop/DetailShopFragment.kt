package com.example.konsinyasiapp.ui.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.konsinyasiapp.databinding.FragmentDetailShopBinding

class DetailShopFragment : Fragment() {

    private var _binding: FragmentDetailShopBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailShopBinding.inflate(inflater, container, false)
        return binding.root
    }
}