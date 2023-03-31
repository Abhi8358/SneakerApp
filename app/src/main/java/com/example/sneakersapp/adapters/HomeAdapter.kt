package com.example.sneakersapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sneakersapp.databinding.HomeAdapterBinding
import com.example.sneakersapp.model.Sneakers
import javax.inject.Inject

class HomeAdapter @Inject constructor() : RecyclerView.Adapter<HomeAdapter.SneakerAdapterViewHolder>() {

    private var onClickListener: OnClickListener? = null
    class SneakerAdapterViewHolder (homeAdapterBinding: HomeAdapterBinding) : RecyclerView.ViewHolder(homeAdapterBinding.root) {
        val binding: HomeAdapterBinding = homeAdapterBinding
    }

    var differentCallback = object : DiffUtil.ItemCallback<Sneakers>() {
        override fun areItemsTheSame(oldItem: Sneakers, newItem: Sneakers): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Sneakers,
            newItem: Sneakers
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differentCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SneakerAdapterViewHolder {
        val view = SneakerAdapterViewHolder(
            HomeAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return view
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SneakerAdapterViewHolder, position: Int) {
        val sneaker = differ.currentList[position]
        holder.itemView.apply {

            Glide.with(this).load(sneaker.gridPictureUrl).into(holder.binding.sneakerImage)

            holder.binding.sneaker = sneaker
        }
        setClickListener(holder.binding, sneaker)
    }

    private fun setClickListener(homeAdapterBinding: HomeAdapterBinding, sneaker: Sneakers) {

        homeAdapterBinding.homeAdapterContainer.setOnClickListener {
            onClickListener?.onClick(sneaker)
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(sneaker: Sneakers)
    }
}