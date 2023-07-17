package com.example.konsinyasiapp.ui.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.konsinyasiapp.R
import com.example.konsinyasiapp.databinding.ActivityMainBinding
import com.example.konsinyasiapp.setting.SettingActivity
import com.example.konsinyasiapp.setting.SettingPrefereces
import com.example.konsinyasiapp.sharedPreferences.UserModel
import com.example.konsinyasiapp.ui.home.HomeViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        private const val USER_MODEL_KEY = "user_model"
    }

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var userModel: UserModel

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<HomeViewModel> {
        HomeViewModel.Factory(SettingPrefereces(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getTheme().observe(this) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

        // Mengambil data dari SharedPreferences
        val userName = sharedPreferences.getString("user_name", null)

        //  Menampilkan toast saat pengguna memasukkan data kedalam SharedPreferences
        Toast.makeText(this, "Halo, $userName", Toast.LENGTH_SHORT).show()

        // Inisialisasi UserModel
        userModel = UserModel(name = userName)

        // Mengubah teks pada nav_header_main
        val headerView = binding.navView.getHeaderView(0)
        val textViewUsername = headerView.findViewById<TextView>(R.id.tv_nav_header_name)

        textViewUsername.text = userModel.name ?: "Nama Pengguna"

        setSupportActionBar(binding.appBarMain.toolbar)

//        binding.appBarMain..setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_shop, R.id.nav_deposit, R.id.nav_myProduct, R.id.nav_kategori_produk
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                Intent(this, SettingActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


}