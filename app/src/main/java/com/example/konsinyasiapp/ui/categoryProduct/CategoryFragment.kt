package com.example.konsinyasiapp.ui.categoryProduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.databinding.FragmentCategoryBinding
import com.example.konsinyasiapp.databinding.FragmentDepositBinding
import com.example.konsinyasiapp.ui.deposit.DepositViewModel


class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }
}