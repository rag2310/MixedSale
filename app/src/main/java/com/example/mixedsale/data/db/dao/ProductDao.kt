package com.example.mixedsale.data.db.dao

import androidx.room.*
import com.example.mixedsale.data.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM PRODUCTS")
    fun allProducts(): Flow<List<Product>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(vararg product: Product)

    @Update
    suspend fun updateProduct(vararg product: Product)

    @Query("DELETE FROM PRODUCTS")
    suspend fun deleteAll()
}