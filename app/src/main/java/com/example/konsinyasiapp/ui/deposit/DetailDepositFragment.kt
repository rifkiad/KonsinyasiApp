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
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.adapter.DepositDetailAdapter
import com.example.konsinyasiapp.databinding.FragmentDetailDepositBinding
import com.example.konsinyasiapp.entities.DepositWithProduct
import com.example.konsinyasiapp.entities.DepositWithShop
import com.example.konsinyasiapp.entities.ProductInDeposit
import com.example.konsinyasiapp.viewModel.DepositViewModel
import com.example.konsinyasiapp.viewModel.ProductInDepositViewModel
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailDepositFragment : Fragment() {

    private var _binding: FragmentDetailDepositBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailDepositFragmentArgs>()

    private val productInDepositViewModel: ProductInDepositViewModel by viewModels()
    private val depositViewModel: DepositViewModel by viewModels()

    private lateinit var depositDetailAdapter: DepositDetailAdapter

    private var idDeposit = 0L
    private var listData = listOf<DepositWithProduct>()
    private var totalSoldProduct: Long = 0L

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
        args.currentItem.let { depositWithShop ->
            depositWithShop.let {
                idDeposit = it.depositData.id
                setupUI(it)
                depositViewModel.getUpdateStatusDeposit(it.depositData.statusDeposit)
            }
        }

        setupAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        setupListeners()
        setupMenuProvider()
    }

    private fun setupAdapter() {
        depositDetailAdapter = DepositDetailAdapter(emptyList())
        depositDetailAdapter.setDetailBinding(binding)
    }

    private fun setupUI(dataDepositWithShop: DepositWithShop) {
        val depositWithShop = args.currentItem
        binding.apply {
            tvNamaTokoDepositDetail.text = depositWithShop.shopData?.name
            tvTanggalDepositDepositDetail.text = depositWithShop.depositData.depositDate
            tvFinishDepositDetail.text = depositWithShop.depositData.depositFinish
            depositViewModel.getUpdateStatusDeposit(dataDepositWithShop.depositData.statusDeposit)
        }
    }

    private fun setupObservers() {
        val depositId = args.currentItem.depositData.id
        productInDepositViewModel.filterProduct(depositId)
            .observe(viewLifecycleOwner, Observer { data ->
                depositDetailAdapter.setData(data)
                listData = data
            })
    }

    private fun setupListeners() {
        binding.btnDetailDepositProduk.setOnClickListener {
            handleProductReturn()
        }
    }


    private fun handleProductReturn() {
        val isReturnQuantityEmpty = listData.none { it.productInDeposit.returnQuantity != 0L }
        val isReturnQuantityValid = isReturnQuantityValid() && !isReturnQuantityEmpty

        if (isReturnQuantityEmpty) {
            showSnackbar("Isi Dulu Produk Yang Kembali")
        } else if (!isReturnQuantityValid) {
            showSnackbar("Jumlah Kembali melewati batas stok atau tidak valid")
        } else {
            updateDepositData()
            navigateToRincianDeposit()
        }
    }

    private fun isReturnQuantityValid(): Boolean {
        return listData.all { depositWithProduct ->
            val returnQuantity = depositWithProduct.productInDeposit.returnQuantity
            val jumlahQuantity = depositWithProduct.productInDeposit.jumlahQuantity
            returnQuantity in 0..jumlahQuantity
        }
    }

    private fun updateDepositData() {
//        listData.forEach { depositWithProduct ->
//            depositViewModel.updateData(depositWithProduct.productInDeposit)
//        }
        listData.forEach { depositWithProduct ->
            val soldProduct =
                depositWithProduct.productInDeposit.jumlahQuantity - depositWithProduct.productInDeposit.returnQuantity
            depositWithProduct.productInDeposit.soldProduct = soldProduct
            depositViewModel.updateData(depositWithProduct.productInDeposit)
        }

        val depositData = args.currentItem.depositData
        val currentDate = Date()
        val formattedDate = currentDate.formatDate("EEEE, dd-MM-yyyy", Locale("id", "ID"))
        depositData.depositFinish = formattedDate

        totalSoldProduct = listData.sumOf { it.productInDeposit.soldProduct }
        productInDepositViewModel.updateTotalSoldProduct(depositData.id)
    }


    private fun navigateToRincianDeposit() {
        val isReturnQuantityValid = isReturnQuantityValid()
//        listData.forEach {
//            it.productInDeposit.soldProduct = it.productInDeposit.jumlahQuantity - it.productInDeposit.returnQuantity
//        }

        if (isReturnQuantityValid) {
            val jumlahProdukKembali = listData.sumOf { it.productInDeposit.returnQuantity }
            val jumlahProdukDititipkan = listData.sumOf { it.productInDeposit.jumlahQuantity }
            val jumlahProdukTerjual = jumlahProdukDititipkan - jumlahProdukKembali

            val action = DetailDepositFragmentDirections.actionDepositDetailToRincianDeposit(
                idDeposit = args.currentItem.depositData.id,
                currentItem = args.currentItem,
                soldProduct = jumlahProdukTerjual
            )
            findNavController().navigate(action)
        } else {
            showSnackbar("Isi Dulu Produk Yang Kembali atau Jumlah Kembali Tidak Valid")
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun setupRecyclerView() {
        binding.rvProductInDepositDetail.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = depositDetailAdapter
        }
    }

    private fun confirmRemoveItem() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Hapus Item")
            setMessage("Anda yakin ingin menghapus item Deposit ini?")
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

    private fun Date.formatDate(format: String, locale: Locale): String {
        val dateFormat = SimpleDateFormat(format, locale)
        return dateFormat.format(this)
    }

    private fun setupMenuProvider() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}