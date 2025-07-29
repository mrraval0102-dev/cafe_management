package com.example.cafe_project.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cafe_project.Activity.Domain.OrderModel
import com.example.cafe_project.databinding.ItemOrderBinding

class OrderAdapter(private val orders: List<OrderModel>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.binding.apply {
            textOrderId.text = order.orderId
            textItemName.text = order.itemName
            textPrice.text = "₹%.2f".format(order.price)
            textStatus.text = order.status
        }
    }

    override fun getItemCount() = orders.size
}
