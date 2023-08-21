package com.example.konsinyasiapp

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.konsinyasiapp.adapter.DepositDetailAdapter
import com.example.konsinyasiapp.databinding.ItemProductInDepositDetailBinding
import com.example.konsinyasiapp.entities.DepositWithProduct
import com.example.konsinyasiapp.entities.ProductInDeposit
import com.example.konsinyasiapp.ui.deposit.DetailDepositFragmentArgs
import com.example.konsinyasiapp.ui.deposit.DetailDepositFragmentDirections
import com.example.konsinyasiapp.utils.ProductInDepositDiffCallback
import com.google.android.material.snackbar.Snackbar
import java.util.Date
import java.util.Locale

class Code {

//    aku mempunyai sebuah 2 fragment yang namanya yaitu fragment detailDeposit dan rincianDeposit isinya seperti ini
//
//    detailDeposit
//    <?xml version="1.0" encoding="utf-8"?>
//    <layout xmlns:android="http://schemas.android.com/apk/res/android"
//    xmlns:app="http://schemas.android.com/apk/res-auto"
//    xmlns:tools="http://schemas.android.com/tools">
//
//    <data>
//    <variable
//    name="args"
//    type="com.example.konsinyasiapp.ui.deposit.DetailDepositFragmentArgs" />
//    </data>
//
//    <ScrollView
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content">
//
//    <androidx.cardview.widget.CardView
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:layout_margin="@dimen/_10sdp"
//    app:cardBackgroundColor="#344955"
//    app:cardCornerRadius="@dimen/_10sdp"
//    app:cardElevation="@dimen/_8sdp">
//
//    <RelativeLayout
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:padding="@dimen/_16sdp">
//
//    <TextView
//    android:id="@+id/tv_informasi_detail_deposit"
//    style="@style/text2_deposit"
//    android:text="@{args.currentItem.shopData.ownerName}"
//    android:textSize="@dimen/_16ssp" />
//
//    <TextView
//    android:id="@+id/tv_nama_toko_label"
//    style="@style/detail_deposit"
//    android:layout_below="@id/tv_informasi_detail_deposit"
//    android:layout_alignParentStart="true"
//    android:layout_marginTop="@dimen/_6sdp"
//    android:text="@string/nama_toko_deposit_detail" />
//
//    <TextView
//    android:id="@+id/tv_nama_toko_deposit_detail"
//    style="@style/detail_deposit"
//    android:layout_below="@id/tv_informasi_detail_deposit"
//    android:layout_marginTop="@dimen/_6sdp"
//    android:layout_toEndOf="@id/tv_nama_toko_label"
//    android:text="@{args.currentItem.shopData.name}" />
//
//    <TextView
//    android:id="@+id/tv_tanggal_deposit_label"
//    style="@style/text1_deposit"
//    android:layout_below="@id/tv_nama_toko_label"
//    android:layout_alignParentStart="true"
//    android:layout_marginTop="@dimen/_6sdp"
//    android:text="@string/tanggal_deposit_detail_deposit" />
//
//    <TextView
//    android:id="@+id/tv_tanggal_deposit_deposit_detail"
//    style="@style/text1_deposit"
//    android:layout_below="@id/tv_nama_toko_label"
//    android:layout_marginTop="@dimen/_6sdp"
//    android:layout_marginEnd="@dimen/_2sdp"
//    android:layout_toEndOf="@id/tv_tanggal_deposit_label"
//    android:text="@{args.currentItem.depositData.depositDate}" />
//
//    <TextView
//    android:id="@+id/tv_tanggal_selesai_label"
//    style="@style/text_detail"
//    android:layout_below="@id/tv_tanggal_deposit_label"
//    android:layout_alignParentStart="true"
//    android:layout_marginTop="@dimen/_6sdp"
//    android:text="@string/tanggal_selesai_deposit_detail_deposit" />
//
//    <TextView
//    android:id="@+id/tv_finish_deposit_detail"
//    style="@style/text_detail"
//    android:layout_below="@id/tv_tanggal_deposit_label"
//    android:layout_marginTop="@dimen/_6sdp"
//    android:layout_marginEnd="@dimen/_2sdp"
//    android:layout_toEndOf="@id/tv_tanggal_selesai_label"
//    android:layout_marginStart="@dimen/_1sdp"
//    android:text="@{args.currentItem.depositData.depositFinish.isEmpty() ? `Proses` : args.currentItem.depositData.depositFinish}" />
//
//    <TextView
//    android:id="@+id/tv_detail_produk_in_detail_deposit"
//    style="@style/text2_deposit"
//    android:layout_below="@id/tv_finish_deposit_detail"
//    android:layout_marginTop="@dimen/_25sdp"
//    android:text="@string/list_produk" />
//
//    <View
//    android:id="@+id/view_in_deposit_detail"
//    android:layout_width="match_parent"
//    android:layout_height="@dimen/_1sdp"
//    android:layout_below="@id/tv_detail_produk_in_detail_deposit"
//    android:background="@color/white" />
//
//    <androidx.recyclerview.widget.RecyclerView
//    android:id="@+id/rvProductInDepositDetail"
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/view_in_deposit_detail"
//    app:layoutManager="LinearLayoutManager"
//    tools:listitem="@layout/item_product_in_deposit_detail" />
//
//    <Button
//    android:id="@+id/btn_detail_deposit_produk"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/rvProductInDepositDetail"
//    android:layout_alignParentEnd="true"
//    android:backgroundTint="@color/button"
//    android:fontFamily="@font/lato_black"
//    android:text="@string/lanjut"
//    android:textColor="@color/white" />
//    </RelativeLayout>
//    </androidx.cardview.widget.CardView>
//    </ScrollView>
//    </layout>
//
//    dan ini si dari recyclerViewnya
//    <?xml version="1.0" encoding="utf-8"?>
//    <androidx.cardview.widget.CardView
//    xmlns:android="http://schemas.android.com/apk/res/android"
//    xmlns:app="http://schemas.android.com/apk/res-auto"
//    xmlns:tools="http://schemas.android.com/tools"
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    app:cardBackgroundColor="#344955">
//
//    <androidx.constraintlayout.widget.ConstraintLayout
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:layout_margin="@dimen/_2sdp">
//
//    <TextView
//    android:id="@+id/tv_nama_product_in_deposit"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_marginTop="@dimen/_3sdp"
//    android:fontFamily="@font/lato"
//    android:text="Nama Produk                          :"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp"
//    app:layout_constraintBottom_toBottomOf="@id/tvProductName"
//    app:layout_constraintStart_toStartOf="parent"
//    app:layout_constraintTop_toTopOf="parent" />
//
//    <TextView
//    android:id="@+id/tvProductName"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:fontFamily="@font/lato"
//    android:layout_marginStart="@dimen/_3sdp"
//    android:text="Nama Produk Yang Dititipkan"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp"
//    app:layout_constraintStart_toEndOf="@+id/tv_nama_product_in_deposit"
//    app:layout_constraintTop_toTopOf="@+id/tv_nama_product_in_deposit" />
//
//    <TextView
//    android:id="@+id/tv_jumlah_barang_product_in_deposit"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_marginTop="@dimen/_4sdp"
//    android:fontFamily="@font/lato"
//    android:text="Jumlah Produk                       :"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp"
//    app:layout_constraintStart_toStartOf="@+id/tv_nama_product_in_deposit"
//    app:layout_constraintTop_toBottomOf="@id/tv_nama_product_in_deposit" />
//
//    <TextView
//    android:id="@+id/tv_jumlah_product_in_deposit_detail"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_marginStart="@dimen/_3sdp"
//    android:layout_marginTop="@dimen/_4sdp"
//    android:fontFamily="@font/lato"
//    android:text="Jumlah Produk Yang Dititipkan"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp"
//    app:layout_constraintStart_toEndOf="@+id/tv_nama_product_in_deposit"
//    app:layout_constraintTop_toBottomOf="@+id/tvProductName" />
//
//    <TextView
//    android:id="@+id/tv_jumlah_produk_kembali_detail_deposit"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_marginTop="@dimen/_4sdp"
//    android:fontFamily="@font/lato"
//    android:text="Jumlah Produk Kembali    :"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp"
//    app:layout_constraintBottom_toBottomOf="@+id/tv_jumlah_product_kembali_in_deposit_detail"
//    app:layout_constraintEnd_toEndOf="@+id/tv_jumlah_barang_product_in_deposit"
//    app:layout_constraintStart_toStartOf="@+id/tv_jumlah_barang_product_in_deposit"
//    app:layout_constraintTop_toBottomOf="@+id/tv_jumlah_barang_product_in_deposit" />
//
//    <TextView
//    android:id="@+id/tv_jumlah_product_kembali_in_deposit_detail"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_marginTop="5dp"
//    android:fontFamily="@font/lato"
//    android:text="Jumlah Produk Kembali"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp"
//    app:layout_constraintBottom_toBottomOf="@id/tv_jumlah_produk_kembali_detail_deposit"
//    app:layout_constraintStart_toStartOf="@+id/tv_jumlah_product_in_deposit_detail"
//    app:layout_constraintTop_toBottomOf="@+id/tv_jumlah_barang_product_in_deposit" />
//
//    <com.google.android.material.textfield.TextInputLayout
//    android:id="@+id/outlinedTextField"
//    android:layout_width="0dp"
//    android:layout_height="wrap_content"
//    android:layout_marginTop="@dimen/_4sdp"
//    android:layout_marginEnd="@dimen/_8sdp"
//    android:layout_marginBottom="@dimen/_8sdp"
//    android:textColorHint="@color/white"
//    app:boxStrokeWidth="0dp"
//    app:boxStrokeWidthFocused="0dp"
//    app:layout_constraintEnd_toEndOf="parent"
//    app:layout_constraintStart_toStartOf="parent"
//    app:layout_constraintTop_toBottomOf="@id/tv_jumlah_produk_kembali_detail_deposit">
//
//    <com.google.android.material.textfield.TextInputEditText
//    android:id="@+id/et_return_quantity"
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:background="@drawable/white_outline"
//    android:fontFamily="@font/lato"
//    android:hint="@string/input_jumlah_kembali"
//    android:inputType="number"
//    android:textColor="@color/white"
//    android:textColorHint="@color/white" />
//    </com.google.android.material.textfield.TextInputLayout>
//    </androidx.constraintlayout.widget.ConstraintLayout>
//    </androidx.cardview.widget.CardView>
//
//    lalu ini isi dari rincianDepositnya
//    <layout xmlns:android="http://schemas.android.com/apk/res/android"
//    xmlns:app="http://schemas.android.com/apk/res-auto"
//    xmlns:tools="http://schemas.android.com/tools">
//
//    <data>
//    <variable
//    name="data"
//    type="com.example.konsinyasiapp.ui.deposit.RincianDepositFragmentArgs" />
//    </data>
//
//    <androidx.cardview.widget.CardView
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:layout_margin="@dimen/_10sdp"
//    app:cardBackgroundColor="#344955"
//    app:cardCornerRadius="@dimen/_10sdp"
//    app:cardElevation="@dimen/_8sdp">
//
//    <RelativeLayout
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:padding="@dimen/_16sdp">
//
//    <TextView
//    android:id="@+id/tv_nama_toko_rincian_deposit"
//    style="@style/text2_deposit"
//    android:text="@{data.currentItem.shopData.name}"
//    android:textSize="@dimen/_16ssp" />
//
//    <androidx.recyclerview.widget.RecyclerView
//    android:id="@+id/rvProductInDepositRincian"
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/tv_nama_toko_rincian_deposit"
//    android:layout_marginTop="@dimen/_3sdp"
//    tools:listitem="@layout/item_rincian_deposit" />
//
//    <Button
//    android:id="@+id/btn_rincian_deposit"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/rvProductInDepositRincian"
//    android:layout_alignParentEnd="true"
//    android:layout_marginStart="@dimen/_8sdp"
//    android:layout_marginTop="@dimen/_10sdp"
//    android:backgroundTint="@color/button"
//    android:fontFamily="@font/lato_black"
//    android:text="Selesaikan Deposit"
//    android:textColor="@color/white" />
//
//    </RelativeLayout>
//    </androidx.cardview.widget.CardView>
//    </layout>
//
//    dan ini isi dari recyclerViewnya
//    <?xml version="1.0" encoding="utf-8"?>
//    <layout xmlns:android="http://schemas.android.com/apk/res/android"
//    xmlns:app="http://schemas.android.com/apk/res-auto"
//    xmlns:tools="http://schemas.android.com/tools">
//
//    <data>
//    <variable
//    name="dataProductInDeposit"
//    type="com.example.konsinyasiapp.entities.DepositWithProduct" />
//    </data>
//
//    <androidx.cardview.widget.CardView
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    app:cardBackgroundColor="#344955">
//
//    <RelativeLayout
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:layout_margin="@dimen/_2sdp">
//
//    <TextView
//    android:id="@+id/tv_nama_product_in_rincian_deposit"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_marginTop="@dimen/_3sdp"
//    android:fontFamily="@font/lato"
//    android:text="Nama Produk         :"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//
//    <TextView
//    android:id="@+id/tv_data_nama_product_in_rincian_deposit"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_alignParentTop="true"
//    android:layout_alignParentEnd="true"
//    android:layout_marginStart="@dimen/_2sdp"
//    android:layout_marginTop="@dimen/_1sdp"
//    android:layout_marginEnd="@dimen/_10sdp"
//    android:layout_toEndOf="@id/tv_nama_product_in_rincian_deposit"
//    android:ellipsize="end"
//    android:fontFamily="@font/montserrat"
//    android:maxLines="1"
//    android:text="@{dataProductInDeposit.productData.namaProduct}"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//
//    <TextView
//    android:id="@+id/tv_data_jumlah_produk_in_rincian_deposit"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/tv_nama_product_in_rincian_deposit"
//    android:layout_marginTop="@dimen/_4sdp"
//    android:fontFamily="@font/lato"
//    android:text="Jumlah Produk      :"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//
//    <TextView
//    android:id="@+id/tv_jumlah_product_in_deposit_detail"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/tv_nama_product_in_rincian_deposit"
//    android:layout_marginStart="@dimen/_2sdp"
//    android:layout_marginTop="@dimen/_2sdp"
//    android:layout_toEndOf="@id/tv_data_jumlah_produk_in_rincian_deposit"
//    android:fontFamily="@font/montserrat"
//    android:text="@{String.valueOf(dataProductInDeposit.productInDeposit.jumlahQuantity)}"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//
//    <TextView
//    android:id="@+id/tv_jumlah_product_kembali_in_rincian_detail"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/tv_data_jumlah_produk_in_rincian_deposit"
//    android:layout_marginTop="@dimen/_4sdp"
//    android:fontFamily="@font/lato"
//    android:text="Jumlah Produk Kembali  :"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//
//    <TextView
//    android:id="@+id/tv_data_jumlah_product_kembali_deposit_detail"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/tv_data_jumlah_produk_in_rincian_deposit"
//    android:layout_marginStart="@dimen/_2sdp"
//    android:layout_marginTop="@dimen/_2sdp"
//    android:layout_toEndOf="@id/tv_jumlah_product_kembali_in_rincian_detail"
//    android:fontFamily="@font/montserrat"
//    android:text="@{String.valueOf(dataProductInDeposit.productInDeposit.returnQuantity)}"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//
//    <TextView
//    android:id="@+id/tv_jumlah_barang_terjual"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/tv_jumlah_product_kembali_in_rincian_detail"
//    android:layout_marginTop="@dimen/_4sdp"
//    android:fontFamily="@font/lato"
//    android:text="Jumlah Produk Yang Terjual :"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//
//    <TextView
//    android:id="@+id/tv_jumlah_produk_yang_terjual_data"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/tv_jumlah_product_kembali_in_rincian_detail"
//    android:layout_marginStart="@dimen/_2sdp"
//    android:layout_marginTop="@dimen/_2sdp"
//    android:layout_toEndOf="@id/tv_jumlah_barang_terjual"
//    android:fontFamily="@font/montserrat"
//    android:text="@{String.valueOf(dataProductInDeposit.productInDeposit.soldProduct)}"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//
//    <View
//    android:id="@+id/view_rincian_deposit"
//    android:layout_width="match_parent"
//    android:layout_height="@dimen/_1sdp"
//    android:layout_below="@id/tv_jumlah_barang_terjual"
//    android:layout_marginTop="@dimen/_8sdp"
//    android:background="@color/white" />
//    </RelativeLayout>
//    </androidx.cardview.widget.CardView>
//    </layout>
//
//    nah saya ingin menambahkan sebuah logika yang dimana jika user sudah menginput kan angka didalam inputJumlahKembali semisal angka nya itu 5 lalu jumlah produk nya yaitu 15 didalam detailDeposit yang berarti total dari penjualan/jumlah terjual kan 10 nah saya ingin menambahkan jumlah terjual tersebut didalam sini
//    <TextView
//    android:id="@+id/tv_jumlah_barang_terjual"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/tv_jumlah_product_kembali_in_rincian_detail"
//    android:layout_marginTop="@dimen/_4sdp"
//    android:fontFamily="@font/lato"
//    android:text="Jumlah Produk Yang Terjual :"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//
//    <TextView
//    android:id="@+id/tv_jumlah_produk_yang_terjual_data"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/tv_jumlah_product_kembali_in_rincian_detail"
//    android:layout_marginStart="@dimen/_2sdp"
//    android:layout_marginTop="@dimen/_2sdp"
//    android:layout_toEndOf="@id/tv_jumlah_barang_terjual"
//    android:fontFamily="@font/montserrat"
//    android:text="@{String.valueOf(dataProductInDeposit.productInDeposit.soldProduct)}"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//
//    textView diatas yaitu textView dari fragment RincianDeposit khususnya dibagian itemnya
//
//    ini kode yang didalam fragment detailnya
//    class DetailDepositFragment : Fragment() {
//
//        private var _binding: FragmentDetailDepositBinding? = null
//        private val binding get() = _binding!!
//
//        private val args by navArgs<DetailDepositFragmentArgs>()
//
//        private val productInDepositViewModel: ProductInDepositViewModel by viewModels()
//        private val depositViewModel: DepositViewModel by viewModels()
//
//        private lateinit var depositDetailAdapter: DepositDetailAdapter
//
//        private var listData = listOf<DepositWithProduct>()
//
//        private var isDataComplete = false
//
//        private val menuProvider = object : MenuProvider {
//            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
//                menuInflater.inflate(R.menu.detail_deposit_menu, menu)
//            }
//
//            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
//                when (menuItem.itemId) {
//                    R.id.delete_item_detail_deposit -> confirmRemoveItem()
//                }
//                return NavigationUI.onNavDestinationSelected(
//                    menuItem,
//                    requireView().findNavController()
//                )
//            }
//        }
//
//        override fun onCreateView(
//            inflater: LayoutInflater, container: ViewGroup?,
//            savedInstanceState: Bundle?
//        ): View {
//            _binding = FragmentDetailDepositBinding.inflate(inflater, container, false)
//            binding.args = args
//
//            depositDetailAdapter = DepositDetailAdapter(emptyList())
//            depositDetailAdapter.setDetailBinding(binding)
//
//            val menuHost: MenuHost = requireActivity()
//            menuHost.addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
//
//            return binding.root
//        }
//
//        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//            super.onViewCreated(view, savedInstanceState)
//            setupUI()
//            setupRecyclerView()
//            setupObservers()
//            setupListeners()
//        }
//
//        private fun setupUI() {
//            val depositWithShop = args.currentItem
//            binding.apply {
//                tvNamaTokoDepositDetail.text = depositWithShop.shopData?.name
//                tvTanggalDepositDepositDetail.text = depositWithShop.depositData.depositDate
//                tvFinishDepositDetail.text = depositWithShop.depositData.depositFinish
//            }
//        }
//
//        private fun setupObservers() {
//            args.currentItem.depositData.id.let { id ->
//                productInDepositViewModel.filterProduct(id)
//                    .observe(viewLifecycleOwner, Observer { data ->
//                        depositDetailAdapter.setData(data)
//                        listData = data
//                    })
//            }
//        }
//
//        private fun setupListeners() {
//            binding.btnDetailDepositProduk.setOnClickListener {
//                handleProductReturn()
//            }
//
//            depositDetailAdapter.setOnItemClickCallback(object :
//                DepositDetailAdapter.OnItemClickCallback {
//                override fun onButtonUpdateQuantity(data: ProductInDeposit, isEmpty: Boolean) {
//                    if (isEmpty) {
//                        isDataComplete = false
//                    } else {
//                        setupListeners()
//                    }
//                    Log.d("DetailDepositFragment", "Product Deposit: $data, Is Empty: $isEmpty")
//                }
//            })
//        }
//
//        private fun handleProductReturn() {
//            if (isDataComplete()) {
//                val invalidReturnQuantityData =
//                    listData.find { it.productInDeposit.returnQuantity > it.productInDeposit.jumlahQuantity }
//
//                if (invalidReturnQuantityData != null) {
//                    showSnackbar("Jumlah Kembali melewati batas stok")
//                } else {
//                    updateDepositData()
//                    navigateToRincianDeposit()
//                }
//            } else {
//                showSnackbar("Isi Dulu Produk Yang Kembali")
//            }
//        }
//
//        private fun isDataComplete(): Boolean {
//            return listData.any { it.productInDeposit.returnQuantity != 0L }
//        }
//
//        private fun updateDepositData() {
//            // Perbarui data deposit di viewModel
//            listData.forEach { depositWithProduct ->
//                depositViewModel.updateData(depositWithProduct.productInDeposit)
//            }
//
//            // Hitung total produk yang terjual
//            val soldProductQuantity = listData.sumOf { it.productInDeposit.soldProduct }
//
//            // Perbarui data deposit dengan tanggal selesai
//            val depositData = args.currentItem.depositData
//            val currentDate = Date()
//            val formattedDate = currentDate.formatDate("EEEE, dd-MM-yyyy", Locale("id", "ID"))
//            depositData.depositFinish = formattedDate
//        }
//
//        private fun navigateToRincianDeposit() {
//            val action = DetailDepositFragmentDirections.actionDepositDetailToRincianDeposit(
//                idDeposit = args.currentItem.depositData.id,
//                currentItem = args.currentItem,
//                soldProduct = listData.sumOf { it.productInDeposit.soldProduct }
//            )
//            findNavController().navigate(action)
//        }
//
//        private fun showSnackbar(message: String) {
//            Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
//        }
//
//        private fun confirmRemoveItem() {
//            AlertDialog.Builder(requireContext()).apply {
//                setTitle("Hapus Item")
//                setMessage("Anda yakin ingin menghapus item ini?")
//                setPositiveButton("Ya") { dialog, _ ->
//                    deleteSelectedItem()
//                    dialog.dismiss()
//                }
//                setNegativeButton("Batal") { dialog, _ ->
//                    dialog.dismiss()
//                }
//                create().show()
//            }
//        }
//
//        private fun deleteSelectedItem() {
//            args.currentItem.depositData.let { selectedDeposit ->
//                depositViewModel.deleteItem(selectedDeposit)
//                findNavController().popBackStack()
//            }
//        }
//
//        private fun setupRecyclerView() {
//            binding.rvProductInDepositDetail.apply {
//                layoutManager = LinearLayoutManager(requireContext())
//                adapter = depositDetailAdapter
//            }
//        }
//
//        private fun Date.formatDate(format: String, locale: Locale): String {
//            val dateFormat = SimpleDateFormat(format, locale)
//            return dateFormat.format(this)
//        }
//
//        override fun onDestroyView() {
//            super.onDestroyView()
//            _binding = null
//        }
//    }
//
//    dan ini kode didalam rinciannya
//    class RincianDepositFragment : Fragment() {
//
//        private var _binding: FragmentRincianDepositBinding? = null
//        private val binding get() = _binding!!
//
//        private val args: RincianDepositFragmentArgs by navArgs()
//        private val mProductInDeposit: ProductInDepositViewModel by viewModels()
//        private val mDeposit: DepositViewModel by viewModels()
//
//        private lateinit var rincianDepositAdapter: RincianDepositAdapter
//        private var soldProductQuantity: Int = 0
//
//        override fun onCreateView(
//            inflater: LayoutInflater, container: ViewGroup?,
//            savedInstanceState: Bundle?
//        ): View {
//            _binding = FragmentRincianDepositBinding.inflate(inflater, container, false)
//            binding.data = args
//
//            return binding.root
//        }
//
//        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//            super.onViewCreated(view, savedInstanceState)
//            setupListeners()
//
//            // Inisialisasi adapter untuk daftar produk dalam deposit
//            rincianDepositAdapter = RincianDepositAdapter(emptyList(), soldProductQuantity)
//            setupRecyclerView()
//            val idDeposit = args.currentItem.depositData.id
//
//            // Amati perubahan LiveData produk dalam deposit
//            mProductInDeposit.filterProduct(idDeposit).observe(viewLifecycleOwner) { data ->
//                rincianDepositAdapter.setData(data, soldProductQuantity)
//            }
//
//            // Set nama toko pada tampilan
//            val depositWithShop = args.currentItem
//            binding.tvNamaTokoRincianDeposit.text = depositWithShop.shopData?.name
//        }
//
//        private fun setupListeners() {
//            binding.btnRincianDeposit.setOnClickListener {
//                val depositData = args.currentItem.depositData
//
//                // Dapatkan tanggal saat ini dalam format yang diinginkan
//                val formattedDate = Date().formatDate("EEEE, dd-MM-yyyy", Locale("id", "ID"))
//
//                // Memanggil finishDeposit dari dalam coroutine
//                viewLifecycleOwner.lifecycleScope.launch {
//                    mDeposit.finishDeposit(depositData)
//
//                    // Navigasi kembali ke DepositFragment
//                    val action = RincianDepositFragmentDirections.actionRincianDepositToNavDeposit()
//                    findNavController().navigate(action)
//                }
//            }
//        }
//
//        private fun setupRecyclerView() {
//            val recyclerView = binding.rvProductInDepositRincian
//            recyclerView.layoutManager = LinearLayoutManager(requireContext())
//            recyclerView.adapter = this@RincianDepositFragment.rincianDepositAdapter
//        }
//
//        private fun Date.formatDate(format: String, locale: Locale): String {
//            val dateFormat = SimpleDateFormat(format, locale)
//            return dateFormat.format(this)
//        }
//
//        override fun onDestroyView() {
//            super.onDestroyView()
//            _binding = null
//        }
//    }
//    bisakah kamu membantu saya, aku mempunyai sebuah 2 fragment yang namanya yaitu fragment detailDeposit dan rincianDeposit isinya seperti ini
//
//    detailDeposit
//    <?xml version="1.0" encoding="utf-8"?>
//    <layout xmlns:android="http://schemas.android.com/apk/res/android"
//    xmlns:app="http://schemas.android.com/apk/res-auto"
//    xmlns:tools="http://schemas.android.com/tools">
//
//    <data>
//
//    <variable
//    name="args"
//    type="com.example.konsinyasiapp.ui.deposit.DetailDepositFragmentArgs" />
//
//    </data>
//
//    <ScrollView
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content">
//
//    <androidx.cardview.widget.CardView
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:layout_margin="@dimen/_10sdp"
//    app:cardBackgroundColor="#344955"
//    app:cardCornerRadius="@dimen/_10sdp"
//    app:cardElevation="@dimen/_8sdp">
//
//    <RelativeLayout
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:padding="@dimen/_16sdp">
//
//    <TextView
//    android:id="@+id/tv_informasi_detail_deposit"
//    style="@style/text2_deposit"
//    android:text="@{args.currentItem.shopData.ownerName}"
//    android:textSize="@dimen/_16ssp" />
//
//    <TextView
//    android:id="@+id/tv_nama_toko_label"
//    style="@style/detail_deposit"
//    android:layout_below="@id/tv_informasi_detail_deposit"
//    android:layout_alignParentStart="true"
//    android:layout_marginTop="@dimen/_6sdp"
//    android:text="@string/nama_toko_deposit_detail" />
//
//    <TextView
//    android:id="@+id/tv_nama_toko_deposit_detail"
//    style="@style/detail_deposit"
//    android:layout_below="@id/tv_informasi_detail_deposit"
//    android:layout_marginTop="@dimen/_6sdp"
//    android:layout_toEndOf="@id/tv_nama_toko_label"
//    android:text="@{args.currentItem.shopData.name}" />
//
//    <TextView
//    android:id="@+id/tv_tanggal_deposit_label"
//    style="@style/text1_deposit"
//    android:layout_below="@id/tv_nama_toko_label"
//    android:layout_alignParentStart="true"
//    android:layout_marginTop="@dimen/_6sdp"
//    android:text="@string/tanggal_deposit_detail_deposit" />
//
//    <TextView
//    android:id="@+id/tv_tanggal_deposit_deposit_detail"
//    style="@style/text1_deposit"
//    android:layout_below="@id/tv_nama_toko_label"
//    android:layout_marginTop="@dimen/_6sdp"
//    android:layout_marginEnd="@dimen/_2sdp"
//    android:layout_toEndOf="@id/tv_tanggal_deposit_label"
//    android:text="@{args.currentItem.depositData.depositDate}" />
//
//    <TextView
//    android:id="@+id/tv_tanggal_selesai_label"
//    style="@style/text_detail"
//    android:layout_below="@id/tv_tanggal_deposit_label"
//    android:layout_alignParentStart="true"
//    android:layout_marginTop="@dimen/_6sdp"
//    android:text="@string/tanggal_selesai_deposit_detail_deposit" />
//
//    <TextView
//    android:id="@+id/tv_finish_deposit_detail"
//    style="@style/text_detail"
//    android:layout_below="@id/tv_tanggal_deposit_label"
//    android:layout_marginTop="@dimen/_6sdp"
//    android:layout_marginEnd="@dimen/_2sdp"
//    android:layout_toEndOf="@id/tv_tanggal_selesai_label"
//    android:layout_marginStart="@dimen/_1sdp"
//    android:text="@{args.currentItem.depositData.depositFinish.isEmpty() ? `Proses` : args.currentItem.depositData.depositFinish}" />
//
//    <TextView
//    android:id="@+id/tv_detail_produk_in_detail_deposit"
//    style="@style/text2_deposit"
//    android:layout_below="@id/tv_finish_deposit_detail"
//    android:layout_marginTop="@dimen/_25sdp"
//    android:text="@string/list_produk" />
//
//    <View
//    android:id="@+id/view_in_deposit_detail"
//    android:layout_width="match_parent"
//    android:layout_height="@dimen/_1sdp"
//    android:layout_below="@id/tv_detail_produk_in_detail_deposit"
//    android:background="@color/white" />
//
//    <androidx.recyclerview.widget.RecyclerView
//    android:id="@+id/rvProductInDepositDetail"
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/view_in_deposit_detail"
//    app:layoutManager="LinearLayoutManager"
//    tools:listitem="@layout/item_product_in_deposit_detail" />
//
//    <Button
//    android:id="@+id/btn_detail_deposit_produk"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/rvProductInDepositDetail"
//    android:layout_alignParentEnd="true"
//    android:backgroundTint="@color/button"
//    android:fontFamily="@font/lato_black"
//    android:text="@string/lanjut"
//    android:textColor="@color/white" />
//    </RelativeLayout>
//    </androidx.cardview.widget.CardView>
//    </ScrollView>
//    </layout>
//
//    dan ini si dari recyclerViewnya
//    <?xml version="1.0" encoding="utf-8"?>
//    <androidx.cardview.widget.CardView
//    xmlns:android="http://schemas.android.com/apk/res/android"
//    xmlns:app="http://schemas.android.com/apk/res-auto"
//    xmlns:tools="http://schemas.android.com/tools"
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    app:cardBackgroundColor="#344955">
//
//    <androidx.constraintlayout.widget.ConstraintLayout
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:layout_margin="@dimen/_2sdp">
//
//    <TextView
//    android:id="@+id/tv_nama_product_in_deposit"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_marginTop="@dimen/_3sdp"
//    android:fontFamily="@font/lato"
//    android:text="Nama Produk                          :"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp"
//    app:layout_constraintBottom_toBottomOf="@id/tvProductName"
//    app:layout_constraintStart_toStartOf="parent"
//    app:layout_constraintTop_toTopOf="parent" />
//
//    <TextView
//    android:id="@+id/tvProductName"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:fontFamily="@font/lato"
//    android:layout_marginStart="@dimen/_3sdp"
//    android:text="Nama Produk Yang Dititipkan"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp"
//    app:layout_constraintStart_toEndOf="@+id/tv_nama_product_in_deposit"
//    app:layout_constraintTop_toTopOf="@+id/tv_nama_product_in_deposit" />
//
//    <TextView
//    android:id="@+id/tv_jumlah_barang_product_in_deposit"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_marginTop="@dimen/_4sdp"
//    android:fontFamily="@font/lato"
//    android:text="Jumlah Produk                       :"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp"
//    app:layout_constraintStart_toStartOf="@+id/tv_nama_product_in_deposit"
//    app:layout_constraintTop_toBottomOf="@id/tv_nama_product_in_deposit" />
//
//    <TextView
//    android:id="@+id/tv_jumlah_product_in_deposit_detail"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_marginStart="@dimen/_3sdp"
//    android:layout_marginTop="@dimen/_4sdp"
//    android:fontFamily="@font/lato"
//    android:text="Jumlah Produk Yang Dititipkan"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp"
//    app:layout_constraintStart_toEndOf="@+id/tv_nama_product_in_deposit"
//    app:layout_constraintTop_toBottomOf="@+id/tvProductName" />
//
//    <TextView
//    android:id="@+id/tv_jumlah_produk_kembali_detail_deposit"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_marginTop="@dimen/_4sdp"
//    android:fontFamily="@font/lato"
//    android:text="Jumlah Produk Kembali    :"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp"
//    app:layout_constraintBottom_toBottomOf="@+id/tv_jumlah_product_kembali_in_deposit_detail"
//    app:layout_constraintEnd_toEndOf="@+id/tv_jumlah_barang_product_in_deposit"
//    app:layout_constraintStart_toStartOf="@+id/tv_jumlah_barang_product_in_deposit"
//    app:layout_constraintTop_toBottomOf="@+id/tv_jumlah_barang_product_in_deposit" />
//
//    <TextView
//    android:id="@+id/tv_jumlah_product_kembali_in_deposit_detail"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_marginTop="5dp"
//    android:fontFamily="@font/lato"
//    android:text="Jumlah Produk Kembali"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp"
//    app:layout_constraintBottom_toBottomOf="@id/tv_jumlah_produk_kembali_detail_deposit"
//    app:layout_constraintStart_toStartOf="@+id/tv_jumlah_product_in_deposit_detail"
//    app:layout_constraintTop_toBottomOf="@+id/tv_jumlah_barang_product_in_deposit" />
//
//    <com.google.android.material.textfield.TextInputLayout
//    android:id="@+id/outlinedTextField"
//    android:layout_width="0dp"
//    android:layout_height="wrap_content"
//    android:layout_marginTop="@dimen/_4sdp"
//    android:layout_marginEnd="@dimen/_8sdp"
//    android:layout_marginBottom="@dimen/_8sdp"
//    android:textColorHint="@color/white"
//    app:boxStrokeWidth="0dp"
//    app:boxStrokeWidthFocused="0dp"
//    app:layout_constraintEnd_toEndOf="parent"
//    app:layout_constraintStart_toStartOf="parent"
//    app:layout_constraintTop_toBottomOf="@id/tv_jumlah_produk_kembali_detail_deposit">
//
//    <com.google.android.material.textfield.TextInputEditText
//    android:id="@+id/et_return_quantity"
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:background="@drawable/white_outline"
//    android:fontFamily="@font/lato"
//    android:hint="@string/input_jumlah_kembali"
//    android:inputType="number"
//    android:textColor="@color/white"
//    android:textColorHint="@color/white" />
//    </com.google.android.material.textfield.TextInputLayout>
//    </androidx.constraintlayout.widget.ConstraintLayout>
//    </androidx.cardview.widget.CardView>
//
//    lalu ini isi dari rincianDepositnya
//    <layout xmlns:android="http://schemas.android.com/apk/res/android"
//    xmlns:app="http://schemas.android.com/apk/res-auto"
//    xmlns:tools="http://schemas.android.com/tools">
//
//    <data>
//
//    <variable
//    name="data"
//    type="com.example.konsinyasiapp.ui.deposit.RincianDepositFragmentArgs" />
//
//    </data>
//
//    <androidx.cardview.widget.CardView
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:layout_margin="@dimen/_10sdp"
//    app:cardBackgroundColor="#344955"
//    app:cardCornerRadius="@dimen/_10sdp"
//    app:cardElevation="@dimen/_8sdp">
//
//    <RelativeLayout
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:padding="@dimen/_16sdp">
//
//    <TextView
//    android:id="@+id/tv_nama_toko_rincian_deposit"
//    style="@style/text2_deposit"
//    android:text="@{data.currentItem.shopData.name}"
//    android:textSize="@dimen/_16ssp" />
//
//    <androidx.recyclerview.widget.RecyclerView
//    android:id="@+id/rvProductInDepositRincian"
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/tv_nama_toko_rincian_deposit"
//    android:layout_marginTop="@dimen/_3sdp"
//    tools:listitem="@layout/item_rincian_deposit" />
//
//    <Button
//    android:id="@+id/btn_rincian_deposit"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/rvProductInDepositRincian"
//    android:layout_alignParentEnd="true"
//    android:layout_marginStart="@dimen/_8sdp"
//    android:layout_marginTop="@dimen/_10sdp"
//    android:backgroundTint="@color/button"
//    android:fontFamily="@font/lato_black"
//    android:text="Selesaikan Deposit"
//    android:textColor="@color/white" />
//
//    </RelativeLayout>
//    </androidx.cardview.widget.CardView>
//    </layout>
//
//    dan ini isi dari recyclerViewnya
//    <?xml version="1.0" encoding="utf-8"?>
//    <layout xmlns:android="http://schemas.android.com/apk/res/android"
//    xmlns:app="http://schemas.android.com/apk/res-auto"
//    xmlns:tools="http://schemas.android.com/tools">
//
//    <data>
//
//    <variable
//    name="dataProductInDeposit"
//    type="com.example.konsinyasiapp.entities.DepositWithProduct" />
//
//    </data>
//
//    <androidx.cardview.widget.CardView
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    app:cardBackgroundColor="#344955">
//
//    <RelativeLayout
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:layout_margin="@dimen/_2sdp">
//
//    <TextView
//    android:id="@+id/tv_nama_product_in_rincian_deposit"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_marginTop="@dimen/_3sdp"
//    android:fontFamily="@font/lato"
//    android:text="Nama Produk         :"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//
//    <TextView
//    android:id="@+id/tv_data_nama_product_in_rincian_deposit"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_alignParentTop="true"
//    android:layout_alignParentEnd="true"
//    android:layout_marginStart="@dimen/_2sdp"
//    android:layout_marginTop="@dimen/_1sdp"
//    android:layout_marginEnd="@dimen/_10sdp"
//    android:layout_toEndOf="@id/tv_nama_product_in_rincian_deposit"
//    android:ellipsize="end"
//    android:fontFamily="@font/montserrat"
//    android:maxLines="1"
//    android:text="@{dataProductInDeposit.productData.namaProduct}"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//
//    <TextView
//    android:id="@+id/tv_data_jumlah_produk_in_rincian_deposit"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/tv_nama_product_in_rincian_deposit"
//    android:layout_marginTop="@dimen/_4sdp"
//    android:fontFamily="@font/lato"
//    android:text="Jumlah Produk      :"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//
//    <TextView
//    android:id="@+id/tv_jumlah_product_in_deposit_detail"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/tv_nama_product_in_rincian_deposit"
//    android:layout_marginStart="@dimen/_2sdp"
//    android:layout_marginTop="@dimen/_2sdp"
//    android:layout_toEndOf="@id/tv_data_jumlah_produk_in_rincian_deposit"
//    android:fontFamily="@font/montserrat"
//    android:text="@{String.valueOf(dataProductInDeposit.productInDeposit.jumlahQuantity)}"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//
//    <TextView
//    android:id="@+id/tv_jumlah_product_kembali_in_rincian_detail"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/tv_data_jumlah_produk_in_rincian_deposit"
//    android:layout_marginTop="@dimen/_4sdp"
//    android:fontFamily="@font/lato"
//    android:text="Jumlah Produk Kembali  :"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//
//    <TextView
//    android:id="@+id/tv_data_jumlah_product_kembali_deposit_detail"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/tv_data_jumlah_produk_in_rincian_deposit"
//    android:layout_marginStart="@dimen/_2sdp"
//    android:layout_marginTop="@dimen/_2sdp"
//    android:layout_toEndOf="@id/tv_jumlah_product_kembali_in_rincian_detail"
//    android:fontFamily="@font/montserrat"
//    android:text="@{String.valueOf(dataProductInDeposit.productInDeposit.returnQuantity)}"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//
//    <TextView
//    android:id="@+id/tv_jumlah_barang_terjual"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/tv_jumlah_product_kembali_in_rincian_detail"
//    android:layout_marginTop="@dimen/_4sdp"
//    android:fontFamily="@font/lato"
//    android:text="Jumlah Produk Yang Terjual :"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//
//    <TextView
//    android:id="@+id/tv_jumlah_produk_yang_terjual_data"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/tv_jumlah_product_kembali_in_rincian_detail"
//    android:layout_marginStart="@dimen/_2sdp"
//    android:layout_marginTop="@dimen/_2sdp"
//    android:layout_toEndOf="@id/tv_jumlah_barang_terjual"
//    android:fontFamily="@font/montserrat"
//    android:text="@{String.valueOf(dataProductInDeposit.productInDeposit.soldProduct)}"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//
//    <View
//    android:id="@+id/view_rincian_deposit"
//    android:layout_width="match_parent"
//    android:layout_height="@dimen/_1sdp"
//    android:layout_below="@id/tv_jumlah_barang_terjual"
//    android:layout_marginTop="@dimen/_8sdp"
//    android:background="@color/white" />
//    </RelativeLayout>
//    </androidx.cardview.widget.CardView>
//    </layout>
//
//    nah saya ingin menambahkan sebuah logika yang dimana jika user sudah menginput kan angka didalam inputJumlahKembali semisal angka nya itu 5 lalu jumlah produk nya yaitu 15 didalam detailDeposit yang berarti total dari penjualan/jumlah terjual kan 10 nah saya ingin menambahkan jumlah terjual tersebut didalam sini
//    <TextView
//    android:id="@+id/tv_jumlah_barang_terjual"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/tv_jumlah_product_kembali_in_rincian_detail"
//    android:layout_marginTop="@dimen/_4sdp"
//    android:fontFamily="@font/lato"
//    android:text="Jumlah Produk Yang Terjual :"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//
//    <TextView
//    android:id="@+id/tv_jumlah_produk_yang_terjual_data"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_below="@id/tv_jumlah_product_kembali_in_rincian_detail"
//    android:layout_marginStart="@dimen/_2sdp"
//    android:layout_marginTop="@dimen/_2sdp"
//    android:layout_toEndOf="@id/tv_jumlah_barang_terjual"
//    android:fontFamily="@font/montserrat"
//    android:text="@{String.valueOf(dataProductInDeposit.productInDeposit.soldProduct)}"
//    android:textColor="@color/white"
//    android:textSize="@dimen/_13ssp" />
//    textView diatas yaitu textView dari fragment RincianDeposit khususnya dibagian itemnya


}


//private fun deleteProduct() {
//
//}
//
//private fun editText(productData: ProductData) {
//    val builder = AlertDialog.Builder(requireContext())
//    builder.setPositiveButton("Ya") { _, _ ->
//        mProductViewModel.updateData(productData)
//        // Panggil fungsi untuk menampilkan dialog edit produk
//        //showDialogEditProduct(dataProduct)
//    }
//    builder.setNegativeButton("Tidak", null)
//    builder.setTitle("Edit Item?")
//    builder.setMessage("Anda akan mengedit item. Lanjutkan? ")
//    builder.create().show()
//}

//private fun showDialogEditProduct(dataProduct: ProductData) {
//    val binding = EditProductBinding.inflate(layoutInflater)
//    val name = binding.edtNamaProdukEdit
//    val categoryItem = binding.autoCompleteTextViewCategoryEdit
//    val price = binding.edtHargaProdukEdit
//
//    // mengeset data akan muncul di edit text
//    name.setText(dataProduct.namaProduct)
//    categoryItem.setText(dataProduct.categoryId.toString())
//    price.setText(dataProduct.hargaProduct)
//
//    val dialogView = binding.root
//
//    val dialogBuilder = AlertDialog.Builder(requireContext())
//        .setTitle("Edit Product")
//        .setView(dialogView)
//        .setPositiveButton("Ok") { dialog, _ ->
//            dataProduct.namaProduct = name.text.toString()
//            dataProduct.categoryId = categoryItem.text.toString().toInt()
//            dataProduct.hargaProduct = price.text.toString()
//
//            // Update data menggunakan Room Database
//            mProductViewModel.updateData(dataProduct)
//            Snackbar.make(
//                requireView(),
//                "Informasi Telah Terupdate",
//                Snackbar.LENGTH_SHORT
//            ).show()
//        }
//        .setNegativeButton("Cancel", null)
//
//    val dialog = dialogBuilder.create()
//    dialog.show()
//}


//    depositDetailAdapter.setOnItemClickCallback(object : DepositDetailAdapter.OnItemClickCallback {
//        override fun onButtonUpdateQuantity(data: ProductInDeposit, isEmpty: Boolean) {
//            if (isEmpty) {
//                requireView().customSnackbar("Jumlah Produk "), )
//            } else if (data.returnQuantity > data.jumlahQuantity) {
//                requireView().customSnackbar(""),)
//            } else {
//
//            }
//        }
//    })

//            binding.also {
//                val id = depositWithProduct.productInDeposit.id
//                val idDeposit = depositWithProduct.productInDeposit.idDeposit
//                val idProduct = depositWithProduct.productInDeposit.productId
//                val quantity = depositWithProduct.productInDeposit.jumlahQuantity
//                var returnQuantity = depositWithProduct.productInDeposit.returnQuantity


//    fun bind(depositWithProduct: DepositWithProduct) {
//        binding.also {
//            val id = depositWithProduct.productInDeposit.id
//            val idDeposit = depositWithProduct.productInDeposit.idDeposit
//            val idProduct = depositWithProduct.productInDeposit.productId
//            val quantity = depositWithProduct.productInDeposit.jumlahQuantity
//            var returnQuantity = depositWithProduct.productInDeposit.returnQuantity
//
//            it.tvProductName.text = depositWithProduct.productData?.namaProduct
//            it.tvJumlahProductInDepositDetail.text =
//                itemView.context.getString(R.string.format_show_list, quantity.toString())
//            it.tvJumlahProductKembaliInDepositDetail.text =
//                itemView.context.getString(R.string.format_show_list, returnQuantity.toString())
//
//            it.btnDetailDeposit.setOnClickListener {
//                val isEmpty: Boolean
//                val valueReturnQuantity = binding.etReturnQuantity.text.toString()
//                if (valueReturnQuantity.isNotEmpty()) {
//                    returnQuantity = valueReturnQuantity.toInt()
//                    isEmpty = false
//                } else {
//                    isEmpty = true
//                }
//                //returnQuantity = if (valueReturnQuantity.isEmpty()) 0 else valueReturnQuantity.toInt()
//                depositWithProduct.productInDeposit.returnQuantity = returnQuantity
//
//                val productDeposit = ProductInDeposit(
//                    id = id,
//                    idDeposit = idDeposit,
//                    productId = idProduct,
//                    jumlahQuantity = quantity,
//                    returnQuantity = returnQuantity
//                )
//                onItemClickCallback.onButtonUpdateQuantity(productDeposit, isEmpty)
//            }
//        }
//    }
//}

//    private fun setupListeners() {
//        binding.btnDetailDepositProduk.setOnClickListener {
//            if (isDataComplete()) {
//                listData.forEach { depositWithProduct ->
//                    depositViewModel.updateData(depositWithProduct.productInDeposit)
//                }
//                val action = DetailDepositFragmentDirections.actionDepositDetailToRincianDeposit(
//                    idDeposit = args.currentItem.depositData.id,
//                    currentItem = args.currentItem
//                )
//                findNavController().navigate(action)
//            } else {
//                Snackbar.make(
//                    requireView(),
//                    "Isi Dulu Produk Yang Kembali",
//                    Snackbar.LENGTH_SHORT
//                ).show()
//            }
//
//            // Tambahkan bagian ini sebagai pernyataan if terpisah
//            for (data in listData) {
//                if (data.productInDeposit.returnQuantity > data.productInDeposit.jumlahQuantity) {
//                    Snackbar.make(requireView(), "pepeq", Snackbar.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
