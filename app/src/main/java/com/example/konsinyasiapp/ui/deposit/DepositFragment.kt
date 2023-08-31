package com.example.konsinyasiapp.ui.deposit

import android.app.AlertDialog
import android.os.Bundle
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
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.adapter.DepositAdapter
import com.example.konsinyasiapp.databinding.FragmentDepositBinding
import com.example.konsinyasiapp.viewModel.DepositViewModel
import com.google.android.material.snackbar.Snackbar

class DepositFragment : Fragment() {

    private var _binding: FragmentDepositBinding? = null
    private val binding get() = _binding!!

    private val mDepositViewModel: DepositViewModel by viewModels()
    private val depositAdapter: DepositAdapter by lazy { DepositAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDepositBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        mDepositViewModel.getAllShopWithDeposit().observe(viewLifecycleOwner) { data ->
            depositAdapter.setData(data)
            mDepositViewModel.checkDatabaseEmpty(data)
        }

        mDepositViewModel.checkDatabaseEmptyLiveData().observe(viewLifecycleOwner, Observer {
            showEmptyDatabaseViews(it)
        })

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.shop_fragment_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.menu_delete_all -> confirmRemoval()
                }
                return NavigationUI.onNavDestinationSelected(menuItem, view.findNavController())
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun showEmptyDatabaseViews(emptyDatabase: Boolean) {
        binding.noDataImageView.visibility = if (emptyDatabase) View.VISIBLE else View.INVISIBLE
        binding.noDataTextView.visibility = if (emptyDatabase) View.VISIBLE else View.INVISIBLE
    }

    private fun confirmRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Ya") { _, _ ->
            mDepositViewModel.deleteAll()
            showSnackbar("Berhasil Semua Item", 800)
        }
        builder.setNegativeButton("Batal") { _, _ -> }
        builder.setTitle("Hapus semua item?")
        builder.setMessage("Anda akan menghapus seluruh isi. Lanjutkan?")
        builder.create().show()
    }

    private fun setupRecyclerView() {
        binding.rvDeposit.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = depositAdapter
        }
    }

    private fun showSnackbar(message: String, duration: Int) {
        val snackbar = Snackbar.make(requireView(), message, duration)
        snackbar.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
