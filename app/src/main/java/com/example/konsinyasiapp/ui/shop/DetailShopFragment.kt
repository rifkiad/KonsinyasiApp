package com.example.konsinyasiapp.ui.shop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.konsinyasiapp.adapter.DetailShopAdapter
import com.example.konsinyasiapp.adapter.ShopAdapter
import com.example.konsinyasiapp.entities.ShopData
import com.example.konsinyasiapp.databinding.FragmentDetailShopBinding
import com.example.konsinyasiapp.viewModel.DepositViewModel

class DetailShopFragment : Fragment() {

    private val args by navArgs<DetailShopFragmentArgs>()

    private var _binding: FragmentDetailShopBinding? = null
    private val binding get() = _binding!!

    private val shopAdapter: DetailShopAdapter by lazy { DetailShopAdapter()

    }
    private val mDepositViewModel: DepositViewModel by viewModels()


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

        //setup RecyclerView
        setupRecyclerView()

        //observe livedata
        mDepositViewModel.getAllShopWithDeposit().observe(viewLifecycleOwner) { data ->
            shopAdapter.setData(data)
            mDepositViewModel.checkDatabaseEmpty(data)
        }

        mDepositViewModel.checkDatabaseEmptyLiveData().observe(viewLifecycleOwner, Observer {
            showEmptyDatabaseViews(it)
        })

        val shop = arguments?.getParcelable<ShopData>("shop")

        // Mengisi data ke tampilan detail
        binding.tvShopNameDetail.text = shop?.name
        binding.tvShopAddress.text = shop?.address
        binding.tvOwnerName.text = shop?.ownerName
        binding.tvPhoneNumber.text = shop?.phoneNumber
    }

    private fun showEmptyDatabaseViews(emptyDatabase: Boolean) {
        if (emptyDatabase) {
            binding.noDataImageView.visibility = View.VISIBLE
            binding.noDataTextView.visibility = View.VISIBLE
        } else {
            binding.noDataImageView.visibility = View.INVISIBLE
            binding.noDataTextView.visibility = View.INVISIBLE
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvItemDepositDate
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = shopAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}