package com.example.mixedsale.ui.product

import androidx.lifecycle.*
import com.example.mixedsale.data.model.Product
import com.example.mixedsale.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {

    var searchAllProducts: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()

    private var search = MutableLiveData<Boolean>(false)

    fun insert(product: Product) = viewModelScope.launch {
        repository.insert(product)
    }

    fun searchProduct(name: String?) = viewModelScope.launch {
        if (name == null) {
            repository.allProducts.collect {
                searchAllProducts.postValue(it)
            }
        } else {
            repository.searchProducts(name).collect {
                searchAllProducts.postValue(it)
            }
        }
        search.postValue(true)
    }

    fun deleteProducts(list: List<Product>) = viewModelScope.launch {
        val listDelete = list
        listDelete.forEach { product ->
            repository.delete(product)
        }
    }
}

