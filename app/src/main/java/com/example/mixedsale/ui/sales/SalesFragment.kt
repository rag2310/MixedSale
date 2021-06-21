package com.example.mixedsale.ui.sales

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mixedsale.R
import com.example.mixedsale.SalesApplication
import com.example.mixedsale.data.model.Sales
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class SalesFragment : Fragment() {

    private val salesViewModel: SalesViewModel by viewModels {
        SalesViewModelFactory((requireActivity().application as SalesApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sales, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.salesRecyclerView)
        val fab = view.findViewById<FloatingActionButton>(R.id.addSalesFloatingActionButton)
        val adapter = SalesListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        salesViewModel.allSales.observe(viewLifecycleOwner, { sales ->
            sales?.let {
                adapter.submitList(it)
            }
        })

        fab.setOnClickListener {
            val sales3 = Sales(
                id = System.currentTimeMillis() + 2000000, quantity = 0,
                amount = 0.0, creationDate = Calendar.getInstance().time,
                dateOfSale = Calendar.getInstance().time
            )
            salesViewModel.insert(sales3)
        }
    }
}