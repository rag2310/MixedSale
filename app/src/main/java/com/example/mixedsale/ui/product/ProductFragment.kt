package com.example.mixedsale.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mixedsale.data.model.Product
import com.example.mixedsale.databinding.DialogFormProductBinding
import com.example.mixedsale.databinding.FragmentProductBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class ProductFragment : Fragment() {

    private val productViewModel: ProductViewModel by viewModels()
    private lateinit var binding: FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductListAdapter(requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        productViewModel.allProducts.observe(viewLifecycleOwner, { products ->
            products?.let {
                adapter.submitList(it)
                println("Count ${it.size}")
            }
        })

        binding.addProduct.setOnClickListener {
            val binding = DialogFormProductBinding.inflate(
                LayoutInflater.from(requireContext()),
                null, false
            )
            binding.product =
                Product(name = "", price = 0.0, creationDate = Calendar.getInstance().time)
            MaterialAlertDialogBuilder(requireContext())
                .setView(binding.root)
                .setPositiveButton("Ok") { _, _ ->
                    val product: Product = binding.product!!
                    productViewModel.insert(product)
                }.setNegativeButton("NO") { dialog, which ->
                    println("---------> $dialog -> $which")
                }
                .show()
        }
        /*binding.addProduct.setOnClickListener {
                val product = Product(
                    name = "Coca cola",
                    price = 12.0,
                    creationDate = Calendar.getInstance().time
                )
                productViewModel.insert(product)
            }*/
    }
}