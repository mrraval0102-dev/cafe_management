package com.example.cafe_project.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cafe_project.Activity.Domain.FavoriteItemModel
import com.example.cafe_project.Adapter.FavoriteAdapter
import com.example.cafe_project.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var favoriteAdapter: FavoriteAdapter
    private var favoriteItems = mutableListOf<FavoriteItemModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadDummyFavorites()
        setupRecyclerView()
    }

    private fun loadDummyFavorites() {
        // Replace this with data from ViewModel/Firebase in future
        favoriteItems = mutableListOf(
            FavoriteItemModel("Caramel Latte", 180.0, "https://example.com/caramel.png"),
            FavoriteItemModel("Cold Brew", 150.0, "https://example.com/coldbrew.png")
        )
    }

    private fun setupRecyclerView() {
        favoriteAdapter = FavoriteAdapter(favoriteItems)
        binding.favoriteRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.favoriteRecyclerView.adapter = favoriteAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
