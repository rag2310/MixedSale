package com.example.mixedsale.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mixedsale.R
import com.example.mixedsale.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMenuBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this

        val navController = findNavController()

        binding.productCardView.setOnClickListener {
            val action =
                MenuFragmentDirections.actionMenuFragmentToProductFragment()
            navController.navigate(action)
        }


        return binding.root
    }
}