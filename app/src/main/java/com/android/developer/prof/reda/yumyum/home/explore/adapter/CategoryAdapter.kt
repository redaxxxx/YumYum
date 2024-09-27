package com.android.developer.prof.reda.yumyum.home.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.developer.prof.reda.yumyum.databinding.CategoryViewHolderBinding
import com.android.developer.prof.reda.yumyum.home.explore.model.CategoryModel
import com.bumptech.glide.Glide

class CategoryAdapter: ListAdapter<CategoryModel, CategoryAdapter.CategoryViewHolder>(DiffCallback) {

    inner class CategoryViewHolder(private val binding: CategoryViewHolderBinding): ViewHolder(binding.root){
        fun bind(category: CategoryModel){
            Glide.with(itemView)
                .load(category.categoryImg)
                .into(binding.catImg)

            binding.catTitle.text = category.categoryTitle
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<CategoryModel>(){
        override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryViewHolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }
}