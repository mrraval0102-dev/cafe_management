package com.example.cafe_project.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cafe_project.Activity.Domain.ItemsModel
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

        setupRecyclerView()
        updateTotalAmount()

        binding.checkoutButton.setOnClickListener {
            // Add your checkout logic here
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
