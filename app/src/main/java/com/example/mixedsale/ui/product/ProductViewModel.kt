package com.example.mixedsale.ui.product

import androidx.lifecycle.*
import com.example.mixedsale.data.model.Product
import com.example.mixedsale.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {

    val allProducts: LiveData<List<Product>> = repository.allProducts.asLiveData()

    fun insert(product: Product) = viewModelScope.launch {
        repository.insert(product)
    }

    fun test(){
        println("HILT FUNCIONADO VIEWMODEL")
    }
}