package com.example.cafe_project.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cafe_project.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadUserInfo()
    }

    private fun loadUserInfo() {
        // Sample data – replace with shared preferences or Firebase
        val userName = "Mihir Khode"
        val userEmail = "mihir@example.com"
        val userImageUrl = "https://i.pravatar.cc/150?img=3"

        binding.tvName.text = userName
        binding.tvEmail.text = userEmail

        Glide.with(this)
            .load(userImageUrl)
            .circleCrop()
            .into(binding.ivProfile)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
