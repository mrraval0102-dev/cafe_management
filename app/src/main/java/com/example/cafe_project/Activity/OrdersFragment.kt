package com.example.cafe_project.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cafe_project.Activity.Domain.OrderModel
import com.example.cafe_project.Activity.ViewModel.MainViewModel
import com.example.cafe_project.Adapter.OrderAdapter
import com.example.cafe_project.databinding.FragmentOrdersBinding

class OrdersFragment : Fragment() {

    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: OrderAdapter
    private var orders = mutableListOf<OrderModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeOrders()
    }

    private fun setupRecyclerView() {
        adapter = OrderAdapter(orders)
        binding.orderRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.orderRecyclerView.adapter = adapter
    }

    private fun observeOrders() {
        // Show ProgressBar before loading
        binding.progressBar.visibility = View.VISIBLE

        viewModel.loadOrders().observe(viewLifecycleOwner) { orderList ->
            // Hide ProgressBar when data is received
            binding.progressBar.visibility = View.GONE

            orders.clear()
            orders.addAll(orderList)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
