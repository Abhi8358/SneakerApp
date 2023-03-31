package com.example.sneakersapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sneakersapp.databinding.ImageViewBinding

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ImageSliderAdapter(private val imageUrlList: ArrayList<String>) :
    RecyclerView.Adapter<ImageSliderAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(val binding: ImageViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(imageUrl: String) {
            Glide.with(binding.root.context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.sliderImage)
        }
    }

    override fun getItemCount(): Int = imageUrlList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {

        val binding = ImageViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {

        holder.setData(imageUrlList[position])
    }
}
