package com.example.cafe_project.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cafe_project.Activity.DetailActivity
import com.example.cafe_project.Activity.Domain.ItemsModel
import com.example.cafe_project.databinding.ViewholderItemPicLeftBinding
import com.example.cafe_project.databinding.ViewholderItemPicRightBinding

class ItemListCategoryAdapter(private val items: MutableList<ItemsModel>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_ITEM1 = 0
        const val TYPE_ITEM2 = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) TYPE_ITEM1 else TYPE_ITEM2
    }

    class ViewholderItem1(val binding: ViewholderItemPicRightBinding) :
        RecyclerView.ViewHolder(binding.root)

    class ViewholderItem2(val binding: ViewholderItemPicLeftBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        return when (viewType) {
            TYPE_ITEM1 -> {
                val binding = ViewholderItemPicRightBinding.inflate(
                    LayoutInflater.from(context), parent, false
                )
                ViewholderItem1(binding)
            }
            TYPE_ITEM2 -> {
                val binding = ViewholderItemPicLeftBinding.inflate(
                    LayoutInflater.from(context), parent, false
                )
                ViewholderItem2(binding)
            }
            else -> throw IllegalArgumentException("Invalid View type")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        fun bindCommonData(
            holder: RecyclerView.ViewHolder,
            context: Context,
            title: String,
            price: String,
            rating: Float,
            imageUrl: Any
        ) {
            when (holder) {
                is ViewholderItem1 -> {
                    holder.binding.titleTxt.text = title
                    holder.binding.priceTxt.text = price
                    holder.binding.ratingBar.rating = rating
                    Glide.with(context).load(imageUrl).into(holder.binding.picMain)

                    holder.itemView.setOnClickListener {
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra("object", item)
                        context.startActivity(intent)
                    }
                }

                is ViewholderItem2 -> {
                    holder.binding.titleTxt.text = title
                    holder.binding.priceTxt.text = price
                    holder.binding.ratingBar.rating = rating
                    Glide.with(context).load(imageUrl).into(holder.binding.picMain)

                    holder.itemView.setOnClickListener {
                        val intent = Intent(context, DetailActivity::class.java)
                        intent.putExtra("object", item)
                        context.startActivity(intent)
                    }
                }
            }
        }

        val context = holder.itemView.context
        val imageUrl = item.picUrl.firstOrNull() ?: ""

        bindCommonData(
            holder,
            context,
            item.title,
            "${item.price} USD",
            item.rating.toFloat(),
            imageUrl
        )
    }
}
