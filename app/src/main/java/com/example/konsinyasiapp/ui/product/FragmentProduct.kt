package com.example.konsinyasiapp.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.konsinyasiapp.adapter.ProductAdapter
import com.example.konsinyasiapp.adapter.ShopAdapter
import com.example.konsinyasiapp.databinding.FragmentProductBinding
import com.example.konsinyasiapp.entities.ProductData
import com.example.konsinyasiapp.entities.ShopData
import com.example.konsinyasiapp.viewModel.ProductViewModel


class FragmentProduct : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private val mProductViewModel: ProductViewModel by viewModels()


    private var productAdapter: ProductAdapter? = null

    private var list = arrayListOf<ProductData>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productAdapter = ProductAdapter(requireContext(), ArrayList(), mProductViewModel)

        //setup RecyclerView
        setupRecyclerView()

        //observe livedata
        mProductViewModel.getAllProduct.observe(viewLifecycleOwner, Observer { data ->
            productAdapter!!.setData(data as ArrayList<ProductData>)
        })

    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvProduct
        productAdapter?.let {
            recyclerView.adapter = productAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}