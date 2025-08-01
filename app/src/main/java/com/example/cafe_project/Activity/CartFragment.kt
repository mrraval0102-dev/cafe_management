package com.example.cafe_project.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cafe_project.Activity.Domain.ItemsModel
import com.example.cafe_project.Activity.ViewModel.MainViewModel
import com.example.cafe_project.Adapter.CartAdapter
import com.example.cafe_project.databinding.FragmentCartBinding
import com.example.project1762.Helper.ManagmentCart
import com.uilover.project195.Helper.ChangeNumberItemsListener

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private lateinit var cartAdapter: CartAdapter
    private lateinit var managmentCart: ManagmentCart
    private var cartItems = arrayListOf<ItemsModel>()

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        managmentCart = ManagmentCart(requireContext())
        cartItems = managmentCart.getListCart()

        if (cartItems.isEmpty()) {
            Toast.makeText(requireContext(), "Your cart is empty", Toast.LENGTH_SHORT).show()
            binding.cartRecyclerView.visibility = View.GONE
            binding.checkoutButton.isEnabled = false
            binding.cartTotalAmount.text = "$0.00"
            return
        }

        setupRecyclerView()
        updateTotalAmount()

        binding.checkoutButton.setOnClickListener {
            for (item in cartItems) {
                val itemName = item.title
                val price = item.price * item.numberInCart
                val status = "Completed"
                mainViewModel.createOrders(orderId = generateOrderId(),itemName, price, status)
            }

            mainViewModel.orderStatus.observe(viewLifecycleOwner) { success ->
                if (success) {
                    Toast.makeText(requireContext(), "Order placed successfully!", Toast.LENGTH_SHORT).show()

                    // Clear TinyDB cart
                    managmentCart.clearCart()

                    // Clear UI
                    cartItems.clear()
                    cartAdapter.notifyDataSetChanged()
                    updateTotalAmount()
                    binding.checkoutButton.isEnabled = false
                    binding.cartRecyclerView.visibility = View.GONE
                } else {
                    Toast.makeText(requireContext(), "Order failed. Try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(cartItems, requireContext(), object : ChangeNumberItemsListener {
            override fun onChanged() {
                updateTotalAmount()
            }
        })

        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = cartAdapter
    }

    private fun updateTotalAmount() {
        val total = managmentCart.getTotalFee()
        binding.cartTotalAmount.text = "$%.2f".format(total)
    }

    private fun generateOrderId(): String {
        val timestamp = System.currentTimeMillis()
        val randomPart = (1000..9999).random()
        return "ORD-$timestamp-$randomPart"
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
