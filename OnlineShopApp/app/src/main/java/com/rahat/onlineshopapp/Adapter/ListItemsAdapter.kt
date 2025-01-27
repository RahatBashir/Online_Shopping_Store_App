package com.rahat.onlineshopapp.Adapter



import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahat.onlineshopapp.Activity.DetailActivity
import com.rahat.onlineshopapp.Model.ItemsModel
import com.rahat.onlineshopapp.databinding.ViewholderItem1Binding
import com.rahat.onlineshopapp.databinding.ViewholderItem2Binding

class ListItemsAdapter(private val items: MutableList<ItemsModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_ITEM1 = 0
        const val TYPE_ITEM2 = 1
    }

    private var context: android.content.Context? = null
    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) TYPE_ITEM1 else TYPE_ITEM2
    }

    class ViewholderItem1(val binding: ViewholderItem1Binding) :
        RecyclerView.ViewHolder(binding.root)

    class ViewholderItem2(val binding: ViewholderItem2Binding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return when (viewType) {
            TYPE_ITEM1 -> {
                val binding = ViewholderItem1Binding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                ViewholderItem1(binding)
            }

            TYPE_ITEM2 -> {
                val binding = ViewholderItem2Binding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                ViewholderItem2(binding)
            }

            else -> throw IllegalArgumentException("Invalid View Type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        fun bindCommonData(titleTxt:String,priceTxt:String,rating: Float,picUrl:String,logo:String) {

            when (holder) {
                is ViewholderItem1 -> {
                    holder.binding.titleTxt.text = titleTxt
                    holder.binding.priceTxt.text = priceTxt
                    holder.binding.ratingBar.rating = rating

                    Glide.with(holder.itemView.context)
                        .load(picUrl)
                        .into(holder.binding.pic)

                    Glide.with(holder.itemView.context)
                        .load(logo)
                        .into(holder.binding.logo)

                    holder.itemView.setOnClickListener {
                        val intent = Intent(holder.itemView.context, DetailActivity::class.java)
                        intent.putExtra("object",items[position])
                        holder.itemView.context.startActivity(intent)
                    }
                }

                is ViewholderItem2 -> {
                    holder.binding.titleTxt.text = titleTxt
                    holder.binding.priceTxt.text = priceTxt
                    holder.binding.ratingBar.rating = rating

                    Glide.with(holder.itemView.context)
                        .load(picUrl)
                        .into(holder.binding.pic)

                    Glide.with(holder.itemView.context)
                        .load(logo)
                        .into(holder.binding.logo)

                    holder.itemView.setOnClickListener {
                        val intent = Intent(holder.itemView.context,DetailActivity::class.java)
                        intent.putExtra("object",items[position])
                        holder.itemView.context.startActivity(intent)
                    }
                }

            }

            }
        bindCommonData(
            item.title,
            "${item.price} USD",
            item.rating.toFloat(),
            item.picUrl[0],
            item.logo
        )

        }


    override fun getItemCount(): Int = items.size
}
