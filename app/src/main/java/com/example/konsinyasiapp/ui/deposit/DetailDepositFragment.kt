package com.example.konsinyasiapp.ui.deposit

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.adapter.DepositDetailAdapter
import com.example.konsinyasiapp.databinding.FragmentDetailDepositBinding
import com.example.konsinyasiapp.entities.DepositData
import com.example.konsinyasiapp.entities.DepositWithShop
import com.example.konsinyasiapp.viewModel.DepositViewModel
import com.example.konsinyasiapp.viewModel.ProductInDepositViewModel

class DetailDepositFragment : Fragment() {

    private var _binding: FragmentDetailDepositBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailDepositFragmentArgs>()

    private var idDeposit = 0L
    private val mProductInDeposit: ProductInDepositViewModel by viewModels()
    private val mDepositViewModel: DepositViewModel by viewModels()
    private var depositDetailAdapter: DepositDetailAdapter = DepositDetailAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailDepositBinding.inflate(inflater, container, false)
        binding.args = args

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setupRecyclerView
        setupRecyclerView()
        args.currentItem.let { depositWithShop ->
            depositWithShop.let {
                idDeposit = it.depositData.id
            }
        }

        mProductInDeposit.filterProduct(idDeposit).observe(viewLifecycleOwner) { data ->
            depositDetailAdapter.setData(data)
        }

        // Inisialisasi navController
        val navController = findNavController()

        val deposit = arguments?.getParcelable<DepositWithShop>("deposit")

        binding.tvNamaTokoDepositDetail.text = deposit?.shopData?.name
        binding.tvTanggalDepositDepositDetail.text = deposit?.depositData?.depositDate
        binding.tvFinishDepositDetail.text = deposit?.depositData?.depositFinish

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.detail_deposit_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.delete_item_detail_deposit -> confirmRemoveItem()

                }
                return NavigationUI.onNavDestinationSelected(menuItem, view.findNavController())
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.btnDetailDeposit.setOnClickListener {
            val action = DetailDepositFragmentDirections.actionDepositDetailToRincianDeposit(
                idDeposit = idDeposit,
                currentItem = args.currentItem
            )
            navController.navigate(action)
        }
    }


    private fun confirmRemoveItem() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Hapus Item")
        builder.setMessage("Anda yakin ingin menghapus item ini?")
        builder.setPositiveButton("Ya") { dialog, _ ->
            deleteSelectedItem()
            dialog.dismiss()
        }
        builder.setNegativeButton("Batal") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun deleteSelectedItem() {
        val selectedDeposit =
            args.currentItem.depositData
        mDepositViewModel.deleteItem(selectedDeposit)
        findNavController().popBackStack()
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.rvProductInDepositDetail
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = this@DetailDepositFragment.depositDetailAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}