package com.example.sneakersapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sneakersapp.dao.SneakerTable
import com.example.sneakersapp.databinding.CartAdapterBinding
import javax.inject.Inject

class CartAdapter @Inject constructor() : RecyclerView.Adapter<CartAdapter.SneakerAdapterViewHolder>() {

    private var onClickListener: OnClickListener? = null
    class SneakerAdapterViewHolder (cartAdapterBinding: CartAdapterBinding) : RecyclerView.ViewHolder(cartAdapterBinding.root) {
        val binding: CartAdapterBinding = cartAdapterBinding
    }

    var differentCallback = object : DiffUtil.ItemCallback<SneakerTable>() {
        override fun areItemsTheSame(oldItem: SneakerTable, newItem: SneakerTable): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SneakerTable,
            newItem: SneakerTable
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differentCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SneakerAdapterViewHolder {
        val view = SneakerAdapterViewHolder(
            CartAdapterBinding.inflate(
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

    private fun setClickListener(cartAdapterBinding: CartAdapterBinding, sneaker: SneakerTable) {

        cartAdapterBinding.crossButton.setOnClickListener {
            onClickListener?.onClick(sneaker)
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(sneaker: SneakerTable)
    }
}