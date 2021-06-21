package com.example.mixedsale.ui.sales

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mixedsale.R
import com.example.mixedsale.data.model.Sales
import com.example.mixedsale.ui.sales.SalesListAdapter.*

class SalesListAdapter : ListAdapter<Sales, SalesViewHolder>(SalesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesViewHolder {
        return SalesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SalesViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.id.toString())
    }

    class SalesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val salesItemView: TextView = itemView.findViewById(R.id.text_view)

        fun bind(text: String?) {
            salesItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): SalesViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.sales_item, parent, false)
                return SalesViewHolder(view)
            }
        }
    }

    class SalesComparator : DiffUtil.ItemCallback<Sales>() {
        override fun areItemsTheSame(oldItem: Sales, newItem: Sales): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Sales, newItem: Sales): Boolean {
            return oldItem.id == newItem.id
        }
    }
}