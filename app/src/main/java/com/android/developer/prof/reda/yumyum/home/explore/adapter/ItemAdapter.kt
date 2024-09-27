package com.android.developer.prof.reda.yumyum.home.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.developer.prof.reda.yumyum.databinding.BestSellerViewHolderBinding
import com.android.developer.prof.reda.yumyum.home.explore.model.ItemModel
import com.bumptech.glide.Glide

class ItemAdapter: ListAdapter<ItemModel, ItemAdapter.ItemViewHolder>(DiffCallback) {

    inner class ItemViewHolder(private val binding: BestSellerViewHolderBinding): ViewHolder(binding.root){
        fun bind(item: ItemModel){
            Glide.with(itemView)
                .load(item.picUrl[0])
                .into(binding.productImage)

            binding.productNameTv.text = item.title
            binding.productPriceTv.text = "$${item.price}"
            binding.ratingTv.text = item.rating.toString()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<ItemModel>(){
        override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem.categoryId == newItem.categoryId
        }

        override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(BestSellerViewHolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}