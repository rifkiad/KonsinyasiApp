package com.example.konsinyasiapp.ui.shop

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
import com.example.konsinyasiapp.adapter.ShopAdapter
import com.example.konsinyasiapp.databinding.FragmentShopBinding
import com.example.konsinyasiapp.entities.ShopData
import com.example.konsinyasiapp.viewModel.ShopViewModel
import com.google.android.material.snackbar.Snackbar

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    private val mShopViewModel: ShopViewModel by viewModels()
    private lateinit var shopAdapter: ShopAdapter
    private val list = arrayListOf<ShopData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shopAdapter = ShopAdapter(requireContext(), list)
        setupRecyclerView()
        observeLiveData()
        setupMenuProvider()
    }

    private fun observeLiveData() {
        mShopViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            shopAdapter.setData(data as ArrayList<ShopData>)
            mShopViewModel.checkDatabaseEmpty(data)
        })

        mShopViewModel.checkDatabaseEmptyLiveData().observe(viewLifecycleOwner, Observer {
            showEmptyDatabaseViews(it)
        })
    }

    private fun setupMenuProvider() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.shop_fragment_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.menu_delete_all -> confirmRemoval()
                }
                return NavigationUI.onNavDestinationSelected(menuItem, view!!.findNavController())
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun showEmptyDatabaseViews(emptyDatabase: Boolean) {
        binding.noDataImageView.visibility = if (emptyDatabase) View.VISIBLE else View.INVISIBLE
        binding.noDataTextView.visibility = if (emptyDatabase) View.VISIBLE else View.INVISIBLE
    }

    private fun setupRecyclerView() {
        binding.rvShop.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ShopFragment.shopAdapter
        }
    }

    private fun confirmRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Ya") { _, _ ->
            mShopViewModel.deleteAll()
            showSnackbar(
                "Berhasil Semua Item", 800
            )
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Hapus semua item?")
        builder.setMessage("Anda akan menghapus seluruh isi. Lanjutkan?")
        builder.create().show()
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
