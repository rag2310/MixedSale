package com.example.mixedsale.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "sales")
data class Sales(

    @PrimaryKey
    @ColumnInfo(name = "ID_SALES")
    var id: Long,

    @ColumnInfo(name = "QUANTITY")
    var quantity: Int,

    @ColumnInfo(name = "AMOUNT")
    var amount: Double,

    @ColumnInfo(name = "CREATION_DATE")
    var creationDate: Date,

    @ColumnInfo(name = "DATE_OF_SALE")
    var dateOfSale: Date

)