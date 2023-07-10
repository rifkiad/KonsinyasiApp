package com.example.konsinyasiapp.ui.categoryProduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.databinding.FragmentCategoryBinding
import com.example.konsinyasiapp.databinding.FragmentDepositBinding
import com.example.konsinyasiapp.ui.deposit.DepositViewModel


class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val categoryiewModel =
            ViewModelProvider(this).get(CategoryiewModel::class.java)

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.tvCategory
        categoryiewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

}