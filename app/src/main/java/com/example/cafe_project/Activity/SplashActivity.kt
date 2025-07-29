package com.example.cafe_project.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cafe_project.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val skip = sharedPref.getBoolean("skip", false)

        if (skip) {
            startActivity(Intent(this, Login::class.java))
            finish()
            return
        }

        binding.startBtn.setOnClickListener {
            sharedPref.edit().putBoolean("skip", true).apply()
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }
}
