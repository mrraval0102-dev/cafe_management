package com.example.cafe_project.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cafe_project.Activity.Domain.CategoryModel
import com.example.cafe_project.databinding.ViewholderCategoryBinding
import java.util.Locale.Category

class CategoryAdapter(val items:MutableList<CategoryModel>):
    RecyclerView.Adapter<CategoryAdapter.Viewholder>() {

         private lateinit var context: Context
         private var selectedPosition = -1
    private var lastSelectedPosition = -1

    inner class Viewholder(val binding: ViewholderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter
    .Viewholder {
        context = parent.context
        val binding =
            ViewholderCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, position: Int) {
     val item = items[position]
        holder.binding.titleCat.text = item.title

        holder.binding.root.setOnClickListener {
           lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if (selectedPosition == position) {
            holder.binding.titleCat.setBackgroundResource(com.example.cafe_project.R.drawable.dark_brown_bg)
            holder.binding.titleCat.setTextColor(context.resources.getColor(com.example.cafe_project.R.color.white))
        }else{
            holder.binding.titleCat.setBackgroundResource(com.example.cafe_project.R.drawable.white_bg)
            holder.binding.titleCat.setTextColor(context.resources.getColor(com.example.cafe_project.R.color.darkBrown))
        }
    }

    override fun getItemCount(): Int = items.size
}