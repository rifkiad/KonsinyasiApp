package com.example.konsinyasiapp.ui.categoryProduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.databinding.FragmentAddCategoryBinding
import com.example.konsinyasiapp.databinding.FragmentAddShopBinding
import com.example.konsinyasiapp.databinding.FragmentCategoryBinding

class AddCategoryFragment : Fragment() {

    private var _binding: FragmentAddCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_category, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}