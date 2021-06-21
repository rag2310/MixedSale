package com.example.mixedsale.data.db.dao

import androidx.room.*
import com.example.mixedsale.data.model.Sales
import kotlinx.coroutines.flow.Flow

@Dao
interface SalesDao {

    @Query("SELECT * FROM sales")
    fun loadAllSales(): Flow<List<Sales>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSales(vararg sales: Sales)

    @Update
    suspend fun updateSales(vararg sales: Sales)

    @Query("Delete FROM sales")
    suspend fun deleteAll()
}