package com.example.konsinyasiapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.konsinyasiapp.databinding.ActivitySplashScreenBinding
import com.example.konsinyasiapp.sharedPreferences.FormUserPreferenceActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            tvTitipJual.alpha = 0f
            lottieSplash.alpha = 0f

            tvTitipJual.animate().setDuration(2000).alpha(1f)
            lottieSplash.animate().setStartDelay(500).setDuration(2000).alpha(1f).withEndAction {
                val moveToHome =
                    Intent(this@SplashScreenActivity, FormUserPreferenceActivity::class.java)
                startActivity(moveToHome)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }
    }
}
