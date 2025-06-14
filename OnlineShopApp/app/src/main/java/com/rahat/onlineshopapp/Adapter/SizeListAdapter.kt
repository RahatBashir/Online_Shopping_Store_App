package com.rahat.onlineshopapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahat.onlineshopapp.R
import com.rahat.onlineshopapp.databinding.ViewholderSizeBinding

class SizeListAdapter(val items:MutableList<String>):
RecyclerView.Adapter<SizeListAdapter.Viewholder>(){
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context
    class Viewholder(val binding: ViewholderSizeBinding):
    RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeListAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderSizeBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: SizeListAdapter.Viewholder, position: Int) {

        holder.binding.sizeTxt.text = items[position]

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

        }

        if (selectedPosition == position){
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.green_bg2)
            holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.white))
        } else{
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.black))
        }
    }

    override fun getItemCount(): Int = items.size

}
