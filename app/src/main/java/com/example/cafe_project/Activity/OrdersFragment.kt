package com.example.cafe_project.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cafe_project.Activity.Domain.OrderModel
import com.example.cafe_project.Adapter.OrderAdapter
import com.example.cafe_project.databinding.FragmentOrdersBinding

class OrdersFragment : Fragment() {

    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: OrderAdapter
    private var orders = listOf<OrderModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadOrders()
        setupRecyclerView()
    }

    private fun loadOrders() {
        // Replace with ViewModel/Firebase logic later
        orders = listOf(
            OrderModel("ORD001", "Cappuccino", 180.0, "Delivered"),
            OrderModel("ORD002", "Espresso", 150.0, "Processing"),
            OrderModel("ORD003", "Iced Latte", 200.0, "Cancelled")
        )
    }

    private fun setupRecyclerView() {
        adapter = OrderAdapter(orders)
        binding.orderRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.orderRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
