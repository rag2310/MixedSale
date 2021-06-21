package com.example.mixedsale.data.repository

import androidx.annotation.WorkerThread
import com.example.mixedsale.data.db.dao.SalesDao
import com.example.mixedsale.data.model.Sales
import kotlinx.coroutines.flow.Flow

class SalesRepository(private val salesDao: SalesDao) {

    val allSales: Flow<List<Sales>> = salesDao.loadAllSales()

    @WorkerThread
    suspend fun insert(sales: Sales) {
        salesDao.insertSales(sales)
    }
}