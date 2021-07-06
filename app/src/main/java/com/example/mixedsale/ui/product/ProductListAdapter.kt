package com.example.mixedsale.ui.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mixedsale.R
import com.example.mixedsale.data.model.Product
import com.example.mixedsale.databinding.RowProductBinding
import com.example.mixedsale.ui.utils.animators.UsefulAnimations
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ProductListAdapter(private val fragment: FragmentActivity) :
    ListAdapter<Product, ProductListAdapter.ProductViewHolder>(ProductComparator()) {

    companion object {
        private var _listDelete: MutableList<Product> = mutableListOf()
        val listDelete: List<Product>
            get() = _listDelete
    }

    fun getListDelete(): List<Product> {
        return listDelete.toList()
    }

    fun clearListDelete() {
        _listDelete.clear()
        fragment.findViewById<FloatingActionButton>(R.id.add_product).visibility =
            View.VISIBLE
        fragment.findViewById<FloatingActionButton>(R.id.delete_product).visibility =
            View.GONE
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product, fragment)
    }

    open class ProductViewHolder(private val binding: RowProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product, fragment: FragmentActivity) {
            binding.product = product
            binding.checkedView.visibility = View.GONE
            binding.mainCardView.setOnClickListener {

                if (_listDelete.contains(binding.product!!)) {
                    UsefulAnimations.circularRevealInvisible(binding.checkedView)
                    _listDelete.remove(binding.product!!)
                } else {
                    UsefulAnimations.circularRevealVisible(binding.checkedView)
                    _listDelete.add(binding.product!!)
                }

                if (_listDelete.size > 0) {
                    fragment.findViewById<FloatingActionButton>(R.id.add_product).visibility =
                        View.GONE
                    fragment.findViewById<FloatingActionButton>(R.id.delete_product).visibility =
                        View.VISIBLE
                } else {
                    fragment.findViewById<FloatingActionButton>(R.id.add_product).visibility =
                        View.VISIBLE
                    fragment.findViewById<FloatingActionButton>(R.id.delete_product).visibility =
                        View.GONE
                }
            }
            binding.executePendingBindings()
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
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}