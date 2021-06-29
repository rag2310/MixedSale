package com.example.mixedsale.data.repository

import androidx.annotation.WorkerThread
import com.example.mixedsale.data.db.dao.ProductDao
import com.example.mixedsale.data.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ProductRepository @Inject constructor(private val productDao: ProductDao) {

    val allProducts: Flow<List<Product>> = productDao.allProducts()

    @WorkerThread
    suspend fun insert(product: Product) {
        productDao.insertProduct(product)
    }
}