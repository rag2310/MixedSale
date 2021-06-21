package com.example.mixedsale.ui.sales

import androidx.lifecycle.*
import com.example.mixedsale.data.model.Sales
import com.example.mixedsale.data.repository.SalesRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class SalesViewModel(private val repository: SalesRepository) : ViewModel() {

    val allSales: LiveData<List<Sales>> = repository.allSales.asLiveData()

    fun insert(sales: Sales) = viewModelScope.launch {
        repository.insert(sales)
    }
}

class SalesViewModelFactory(private val repository: SalesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SalesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SalesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}