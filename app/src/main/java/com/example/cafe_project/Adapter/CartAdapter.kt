package com.example.cafe_project.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cafe_project.Activity.Domain.CartItemModel
import com.example.cafe_project.databinding.ItemCartBinding

class CartAdapter(
    private val items: MutableList<CartItemModel>,
    private val onCartChanged: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]

        with(holder.binding) {
            itemName.text = item.name
            itemQuantity.text = "Qty: ${item.quantity}"
            itemPrice.text = "₹%.2f".format(item.price * item.quantity)

            removeButton.setOnClickListener {
                items.removeAt(position)
                notifyItemRemoved(position)
                onCartChanged()
            }
        }
    }

    override fun getItemCount() = items.size
}
