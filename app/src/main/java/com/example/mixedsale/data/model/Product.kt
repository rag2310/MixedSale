package com.example.mixedsale.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "PRODUCTS")
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_PRODUCT")
    val id: Long = 0,

    @ColumnInfo(name = "NAME")
    val name: String,

    @ColumnInfo(name = "PRICE")
    val price: Double,

    @ColumnInfo(name = "CREATION_DATE")
    val creationDate: Date
)
