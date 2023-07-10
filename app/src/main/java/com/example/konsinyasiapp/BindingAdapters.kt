package com.example.konsinyasiapp

import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BindingAdapters {

    companion object {
        @BindingAdapter("android:navigateToAddShopFragment")
        @JvmStatic
        fun navigateToAddShopFragment(view: ExtendedFloatingActionButton, navigate: Boolean) {
            view.setOnClickListener {
                if (navigate) {
                    view.findNavController().navigate(R.id.action_nav_shop_to_action_navshop_add)
                }
            }
        }

        @BindingAdapter("android:navigateToAddProductFragment")
        @JvmStatic
        fun navigateToAddProductFragment(view: ExtendedFloatingActionButton, navigate: Boolean) {
            view.setOnClickListener {
                if (navigate) {
                    view.findNavController()
                        .navigate(R.id.action_nav_myProduct_to_action_navProduct_add)
                }
            }
        }

        @BindingAdapter("android:navigateToAddCategoryFragment")
        @JvmStatic
        fun navigateToAddCategoryFragment(view: ExtendedFloatingActionButton, navigate: Boolean) {
            view.setOnClickListener {
                if (navigate) {
                    view.findNavController()
                        .navigate(R.id.action_nav_kategori_produk_to_action_navCategory_add)
                }
            }
        }
    }
}