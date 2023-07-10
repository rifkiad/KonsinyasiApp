package com.example.konsinyasiapp.ui.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.konsinyasiapp.databinding.FragmentShopBinding
import com.example.konsinyasiapp.ui.shop.Adapter.ShopAdapter


class ShopFragment : Fragment() {

    private val mShopViewModel: ShopViewModel by viewModels()

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    private val adapter: ShopAdapter by lazy { ShopAdapter() }

    private lateinit var shopAdapter: ShopAdapter

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setup RecyclerView
        setupRecyclerView()

        //observe livedata
        mShopViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            adapter.setData(data)
        })
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvShop
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//        recyclerView.itemAnimator = LandingAnimator().apply {
//            addDuration = 310
//    }

    }
}