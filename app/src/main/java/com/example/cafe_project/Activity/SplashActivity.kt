package com.example.cafe_project.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cafe_project.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            val sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE)
            val isSkip = sharedPref.getBoolean("skip", false)
            val isLoggedIn = sharedPref.getBoolean("login", false)

            when {
                isSkip && isLoggedIn -> {
                    navigateTo(MainActivity::class.java)
                }
                isSkip -> {
                    navigateTo(Login::class.java)
                }
            }

            binding.startBtn.setOnClickListener {
                sharedPref.edit().putBoolean("skip", true).apply()
                navigateTo(Login::class.java)
            }
        } catch (e: Exception) {
        Log.e("SplashActivity", "Exception during onCreate: ${e.message}", e)
    }


    }

    private fun navigateTo(destination: Class<*>) {
        startActivity(Intent(this, destination))
        finish()
    }
}
