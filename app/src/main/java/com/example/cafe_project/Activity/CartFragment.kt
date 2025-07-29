package com.example.cafe_project.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cafe_project.Activity.Domain.CartItemModel
import com.example.cafe_project.Adapter.CartAdapter
import com.example.cafe_project.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var cartAdapter: CartAdapter
    private var cartItems = mutableListOf<CartItemModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize sample cart data (you should load from DB or ViewModel)
        cartItems = mutableListOf(
            CartItemModel("Espresso", 2, 100.0),
            CartItemModel("Latte", 1, 150.0),
            CartItemModel("Cappuccino", 1, 130.0)
        )

        setupRecyclerView()
        updateTotalAmount()

        binding.checkoutButton.setOnClickListener {
            // Add checkout logic here
        }
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(cartItems) {
            updateTotalAmount()
        }

        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = cartAdapter
    }

    private fun updateTotalAmount() {
        val total = cartItems.sumOf { it.price * it.quantity }
        binding.cartTotalAmount.text = "₹%.2f".format(total)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
