package com.example.konsinyasiapp.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.konsinyasiapp.adapter.DetailShopAdapter
import com.example.konsinyasiapp.databinding.FragmentDetailShopBinding
import com.example.konsinyasiapp.entities.ShopData
import com.example.konsinyasiapp.viewModel.ShopViewModel

class DetailShopFragment : Fragment() {

    private val args by navArgs<DetailShopFragmentArgs>()
    private var _binding: FragmentDetailShopBinding? = null
    private val binding get() = _binding!!

    private var idShop = 0

    private val shopAdapter = DetailShopAdapter()
    private val mShopViewModel: ShopViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailShopBinding.inflate(inflater, container, false)
        binding.args = args
        args.currentItem.let { currentItem ->
            idShop = currentItem.id
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeDeposits()
        observeEmptyDatabase()
    }

    private fun setupUI() {
        val shop = takeArguments()
        shop?.let {
            binding.apply {
                tvShopNameDetail.text = it.name
                tvShopAddress.text = it.address
                tvOwnerName.text = it.ownerName
                tvPhoneNumber.text = it.phoneNumber
            }
        }
        setupRecyclerView()
    }

    private fun takeArguments(): ShopData? {
        return arguments?.getParcelable("shop")
    }

    private fun setupRecyclerView() {
        binding.rvItemDepositDate.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = shopAdapter
        }
    }

    private fun observeDeposits() {
        mShopViewModel.getAllDepositInShop(shopId = idShop)
            .observe(viewLifecycleOwner) { dataDeposit ->
                mShopViewModel.checkDepositInShop(dataDeposit)
                shopAdapter.setData(dataDeposit)
            }
    }

    private fun observeEmptyDatabase() {
        mShopViewModel.checkDepositInShopLiveData().observe(viewLifecycleOwner) {
            showEmptyDatabaseViews(it)
        }
    }

    private fun showEmptyDatabaseViews(emptyDatabase: Boolean) {
        binding.noDataTextView.visibility = if (emptyDatabase) View.VISIBLE else View.INVISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
