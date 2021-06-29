package com.example.mixedsale.ui.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mixedsale.R
import com.example.mixedsale.data.model.Product
import com.example.mixedsale.databinding.RowProductBinding

class ProductListAdapter constructor(private val context: Context) :
    ListAdapter<Product, ProductListAdapter.ProductViewHolder>(ProductComparator()) {

    private var lastPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
        setAnimation(holder.itemView, position)
    }

    override fun onViewDetachedFromWindow(holder: ProductViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    private fun setAnimation(itemView: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.row_slide_in_right)
            animation.interpolator = AccelerateDecelerateInterpolator()
            itemView.startAnimation(animation)
            lastPosition = position
        }
    }

    class ProductViewHolder(private val binding: RowProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.product = product
        }

        companion object {
            fun create(parent: ViewGroup): ProductViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: RowProductBinding = RowProductBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
                return ProductViewHolder(binding)
            }
        }
    }

    class ProductComparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem

        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

    }
}