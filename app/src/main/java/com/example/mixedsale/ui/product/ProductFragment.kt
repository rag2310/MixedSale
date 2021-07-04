package com.example.mixedsale.ui.product

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mixedsale.data.model.Product
import com.example.mixedsale.databinding.DialogFormProductBinding
import com.example.mixedsale.databinding.FragmentProductBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint


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
        binding.viewModel = productViewModel
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
            }
        })

        productViewModel.searchAllProducts.observe(viewLifecycleOwner, { products ->
            products?.let {
                adapter.submitList(it)
            }
        })

        binding.productSearchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    productViewModel.searchProduct("%$query%")
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    productViewModel.searchProduct("%$newText%")
                }
                return true
            }

        })

        binding.addProduct.setOnClickListener {
            val binding = DialogFormProductBinding.inflate(
                LayoutInflater.from(requireContext()),
                null, false
            )

            binding.apply {
                product = Product(name = "", price = 0.0)
                errorProductName = null
                errorProductPrice = null
            }

            val dialog = MaterialAlertDialogBuilder(requireContext())
                .setView(binding.root)
                .show()

            binding.okAction.setOnClickListener {
                binding.product?.let {
                    if (it.name.isEmpty()) {
                        binding.errorProductName = "Empty field"
                        return@let
                    }
                    if (it.price == 0.0) {
                        binding.errorProductPrice = "Price is zero"
                        return@let
                    }
                    productViewModel.insert(it)
                    dialog.dismiss()
                }
            }
            binding.cancelAction.setOnClickListener { dialog.dismiss() }
        }
    }
}