package com.example.konsinyasiapp.databinding.adapter

import android.widget.Button
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.entities.DepositData
import com.example.konsinyasiapp.entities.DepositWithShop
import com.example.konsinyasiapp.entities.ShopData
import com.example.konsinyasiapp.ui.deposit.DepositFragmentDirections
import com.example.konsinyasiapp.ui.deposit.DetailDepositFragmentDirections
import com.example.konsinyasiapp.ui.shop.ShopFragmentDirections
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

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

        @BindingAdapter("android:navigateToAddDepositFragment")
        @JvmStatic
        fun navigateToAddDepositFragment(view: ExtendedFloatingActionButton, navigate: Boolean) {
            view.setOnClickListener {
                if (navigate) {
                    view.findNavController()
                        .navigate(R.id.action_nav_deposit_to_deposit_add)

                }
            }
        }

        @BindingAdapter("android:navigateToRincianDeposit")
        @JvmStatic
        fun navigateToRincianDeposit(button: Button, depositWithShop: DepositWithShop?) {
            button.setOnClickListener {
                depositWithShop?.let {
                    val action =
                        DetailDepositFragmentDirections.actionDepositDetailToRincianDeposit(
                            idDeposit = it.depositData.id,
                            currentItem = it
                        )
                    button.findNavController().navigate(action)
                }
            }
        }
    }
}

//        @BindingAdapter("android:navigateToDetailDeposit")
//        @JvmStatic
//        fun navigateToDetailDeposit(view: RelativeLayout, depositData: DepositWithStoreData) {
//            view.setOnClickListener {
//                val action = DepositFragmentDirections.actionDepositFragmentToDetailDepositFragment(
//                    depositData
//                )
//                view.findNavController().navigate(action)
//            }
//        }
//    }

//@BindingAdapter("android:navigateToRincianDeposit")
//@JvmStatic
//fun navigateToRincianDeposit(button: Button, navigate: Boolean) {
//    button.setOnClickListener {
//        if (navigate) {
//            button.findNavController()
//                .navigate(R.id.action_depositDetail_to_rincian_deposit)
//        }
//    }
//}

