package com.android.developer.prof.reda.yumyum.home.explore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.developer.prof.reda.yumyum.databinding.SliderItemContainerBinding
import com.android.developer.prof.reda.yumyum.home.explore.model.SliderModel
import com.bumptech.glide.Glide

class SliderAdapter: ListAdapter<SliderModel, SliderAdapter.SliderViewHolder>(DiffCallback) {

    inner class SliderViewHolder(private val binding: SliderItemContainerBinding): ViewHolder(binding.root){
        fun bind(slider: SliderModel){
            Glide.with(itemView)
                .load(slider.url)
                .into(binding.sliderImage)
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<SliderModel>(){
        override fun areItemsTheSame(oldItem: SliderModel, newItem: SliderModel): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: SliderModel, newItem: SliderModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(SliderItemContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val slider = getItem(position)
        holder.bind(slider)
    }
}