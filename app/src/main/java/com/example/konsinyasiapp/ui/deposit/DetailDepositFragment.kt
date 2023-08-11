package com.example.konsinyasiapp.ui.deposit

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.adapter.DepositDetailAdapter
import com.example.konsinyasiapp.databinding.FragmentDetailDepositBinding
import com.example.konsinyasiapp.viewModel.DepositViewModel
import com.example.konsinyasiapp.viewModel.ProductInDepositViewModel
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.konsinyasiapp.entities.ProductInDeposit
import com.google.android.material.snackbar.Snackbar

class DetailDepositFragment : Fragment() {

    private var _binding: FragmentDetailDepositBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailDepositFragmentArgs>()

    private val productInDepositViewModel: ProductInDepositViewModel by viewModels()
    private val depositViewModel: DepositViewModel by viewModels()

    private lateinit var depositDetailAdapter: DepositDetailAdapter

    private val menuProvider = object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.detail_deposit_menu, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            when (menuItem.itemId) {
                R.id.delete_item_detail_deposit -> confirmRemoveItem()
            }
            return NavigationUI.onNavDestinationSelected(
                menuItem,
                requireView().findNavController()
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailDepositBinding.inflate(inflater, container, false)
        binding.args = args

        depositDetailAdapter = DepositDetailAdapter(emptyList())
        depositDetailAdapter.setDetailBinding(binding)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupRecyclerView()
        setupObservers()
        setupListeners()

        depositDetailAdapter.setOnItemClickCallback(object :
            DepositDetailAdapter.OnItemClickCallback {
            override fun onButtonUpdateQuantity(data: ProductInDeposit, isEmpty: Boolean) {
                if (isEmpty) {
                    Snackbar.make(
                        requireView(),
                        "Isi Dulu Produk Yang Kembali",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    depositViewModel.updateData(data)
                }
            }
        })
    }

    private fun setupUI() {
        val depositWithShop = args.currentItem
        binding.apply {
            tvNamaTokoDepositDetail.text = depositWithShop.shopData?.name
            tvTanggalDepositDepositDetail.text = depositWithShop.depositData.depositDate
            tvFinishDepositDetail.text = depositWithShop.depositData.depositFinish
        }
    }

    private fun setupObservers() {
        args.currentItem.depositData.id.let { id ->
            productInDepositViewModel.filterProduct(id).observe(viewLifecycleOwner) { data ->
                depositDetailAdapter.setData(data)
            }
        }
    }

    private fun setupListeners() {
        binding.btnDetailDepositProduk.setOnClickListener {
            val action = DetailDepositFragmentDirections.actionDepositDetailToRincianDeposit(
                idDeposit = args.currentItem.depositData.id,
                currentItem = args.currentItem
            )
            findNavController().navigate(action)
        }
    }


    private fun confirmRemoveItem() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Hapus Item")
            setMessage("Anda yakin ingin menghapus item ini?")
            setPositiveButton("Ya") { dialog, _ ->
                deleteSelectedItem()
                dialog.dismiss()
            }
            setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            create().show()
        }
    }

    private fun deleteSelectedItem() {
        args.currentItem.depositData.let { selectedDeposit ->
            depositViewModel.deleteItem(selectedDeposit)
            findNavController().popBackStack()
        }
    }

    private fun setupRecyclerView() {
        binding.rvProductInDepositDetail.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = depositDetailAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
