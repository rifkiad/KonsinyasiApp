package com.example.konsinyasiapp.sharedPreferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.konsinyasiapp.ui.main.MainActivity
import com.example.konsinyasiapp.databinding.ActivityFormUserPreferenceBinding

class FormUserPreferenceActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityFormUserPreferenceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormUserPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

        // Cek apakah pengguna sudah pernah masuk sebelumnya
        val isFirstTime = sharedPreferences.getBoolean("is_first_time", true)
        if (!isFirstTime) {
            startMainActivity()
            finish()
        }

        binding.btnSave.setOnClickListener {

            val userName = binding.edtName.text.toString().trim()

            // Simpan nilai userName ke dalam SharedPreferences
            sharedPreferences.edit().putString("user_name", userName).apply()

            // Simpan status isFirstTime menjadi false
            sharedPreferences.edit().putBoolean("is_first_time", false).apply()

            val intent = Intent(this@FormUserPreferenceActivity, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}

