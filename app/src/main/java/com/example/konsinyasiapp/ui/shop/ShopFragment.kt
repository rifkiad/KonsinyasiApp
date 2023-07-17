package com.example.konsinyasiapp.ui.shop

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
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.entities.ShopData
import com.example.konsinyasiapp.databinding.FragmentShopBinding
import com.example.konsinyasiapp.adapter.ShopAdapter
import com.example.konsinyasiapp.viewModel.ShopViewModel
import com.google.android.material.snackbar.Snackbar


class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    private val mShopViewModel: ShopViewModel by viewModels()


    private lateinit var shopAdapter: ShopAdapter

    private var list = arrayListOf<ShopData>()

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

        shopAdapter = ShopAdapter(requireContext(), list)

        //setup RecyclerView
        setupRecyclerView()


        //observe livedata
        mShopViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            shopAdapter.setData(data as ArrayList<ShopData>)
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
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    private fun setupRecyclerView() {
        val recyclerView = binding.rvShop
        recyclerView.adapter = shopAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun confirmRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mShopViewModel.deleteAll()
            Snackbar.make(
                requireView(),
                "Succsecfully Removed Everything!",
                Snackbar.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete Everything?")
        builder.setMessage("Are you sure you want remove this Everything")
        builder.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}