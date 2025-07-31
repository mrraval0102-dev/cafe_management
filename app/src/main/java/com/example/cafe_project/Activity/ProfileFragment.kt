package com.example.cafe_project.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cafe_project.Activity.ViewModel.UserViewModel
import com.example.cafe_project.R
import com.example.cafe_project.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref: SharedPreferences = requireActivity().getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val userId = sharedPref.getString("userId", null)

        if (userId != null) {
            loadUserInfo(userId)
        } else {
            binding.tvName.text = "Guest"
            binding.tvEmail.text = "Not logged in"
        }

        binding.btnLogout.setOnClickListener {
            sharedPref.edit().clear().apply()

            val intent = Intent(requireActivity(), Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    private fun loadUserInfo(userId: String) {
        binding.progressBarProfile.visibility = View.VISIBLE

        viewModel.loadProfile(userId).observe(viewLifecycleOwner) { user ->
            binding.progressBarProfile.visibility = View.GONE

            if (user != null) {
                val fullName = "${user.firstName} ${user.lastName}"
                binding.tvName.text = fullName
                binding.tvEmail.text = user.email

                binding.ivProfile.setImageResource(R.drawable.sharp_person_24)

            } else {
                binding.tvName.text = "Guest"
                binding.tvEmail.text = "Not logged in"
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
