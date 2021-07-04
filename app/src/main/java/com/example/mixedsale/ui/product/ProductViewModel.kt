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
    val allProducts: LiveData<List<Product>> = repository.allProducts.asLiveData()

    var checkedBtnObs = MutableLiveData(2131296365)

    fun insert(product: Product) = viewModelScope.launch {
        repository.insert(product)
    }

    fun searchProduct(name: String) = viewModelScope.launch {
        repository.searchProducts(name).collect {
            searchAllProducts.postValue(it)
        }
    }
}

