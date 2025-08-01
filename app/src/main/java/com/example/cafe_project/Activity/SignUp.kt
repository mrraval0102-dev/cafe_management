package com.example.cafe_project.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cafe_project.Activity.ViewModel.UserViewModel
import com.example.cafe_project.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            val firstName = binding.etFirstName.text.toString().trim()
            val lastName = binding.etLastName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Signup successful!", Toast.LENGTH_SHORT).show()
                viewModel.signup(firstName, lastName, email, password)
            }
        }

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

        viewModel.signupStatus.observe(this) { success ->
            if (success) {
                Toast.makeText(this, "Signup successful!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Signup failed. Try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
