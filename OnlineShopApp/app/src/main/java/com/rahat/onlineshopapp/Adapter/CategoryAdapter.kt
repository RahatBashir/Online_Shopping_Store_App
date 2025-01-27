package com.rahat.onlineshopapp.Adapter

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahat.onlineshopapp.Activity.ListItemsActivity
import com.rahat.onlineshopapp.Model.CategoryModel
import com.rahat.onlineshopapp.R
import com.rahat.onlineshopapp.databinding.ViewholderCategoryBinding
import android.os.Handler

class CategoryAdapter(private val items:MutableList<CategoryModel>)
    :RecyclerView.Adapter<CategoryAdapter.Viewholder>() {
    inner class Viewholder(val binding: ViewholderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var selectedPosition = -1
    private var lastSelectedPosition = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.Viewholder {
        val binding = ViewholderCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, position: Int) {
        val item = items[position]
        holder.binding.titleCat.text = item.title

        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.picCat)

        if (selectedPosition == position) {
            holder.binding.picCat.setBackgroundResource(R.drawable.green_bg)
            ImageViewCompat.setImageTintList(
                holder.binding.picCat,
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.white
                    )
                )
            )
        } else {
            holder.binding.picCat.setBackgroundResource(R.drawable.grey_circle_bg)
            ImageViewCompat.setImageTintList(
                holder.binding.picCat,
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.black
                    )
                )
            )
        }
        holder.binding.root.setOnClickListener {
            val position = holder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                lastSelectedPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(selectedPosition)
            }
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(holder.itemView.context,ListItemsActivity::class.java).apply {
                    putExtra("id",item.id.toString())
                    putExtra("title",item.title)
                }
                ContextCompat.startActivity(holder.itemView.context,intent,null)
            },500)
        }
    }


    override fun getItemCount(): Int = items.size

}