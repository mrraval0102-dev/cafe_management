package com.example.cafe_project.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cafe_project.Activity.ViewModel.UserViewModel
import com.example.cafe_project.databinding.ActivityLoginBinding
import androidx.core.content.edit

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: UserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPref: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.login(email, password)
            }
        }

        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }

        viewModel.signupStatus.observe(this) { success ->
            if (success) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                sharedPref.edit { putBoolean("login", true) }
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Toast.makeText(this, "Login failed. Try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}