package com.example.cafe_project.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cafe_project.Activity.Domain.FavoriteItemModel
import com.example.cafe_project.databinding.ItemFavoriteBinding

class FavoriteAdapter(private val items: List<FavoriteItemModel>) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            favoriteName.text = item.name
            favoritePrice.text = "₹%.2f".format(item.price)
            Glide.with(favoriteImage.context).load(item.imageUrl).into(favoriteImage)
        }
    }

    override fun getItemCount() = items.size
}
