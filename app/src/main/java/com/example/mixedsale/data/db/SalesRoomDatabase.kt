package com.example.mixedsale.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mixedsale.data.db.converter.Converts
import com.example.mixedsale.data.db.dao.ProductDao
import com.example.mixedsale.data.db.dao.SalesDao
import com.example.mixedsale.data.db.dao.UserDao
import com.example.mixedsale.data.model.Product
import com.example.mixedsale.data.model.Sales
import com.example.mixedsale.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [User::class, Sales::class, Product::class], version = 1, exportSchema = false)
@TypeConverters(Converts::class)
abstract class SalesRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun salesDao(): SalesDao
    abstract fun productDao(): ProductDao

    private class SalesDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { salesRoomDatabase ->
                scope.launch {

                    val salesDao = salesRoomDatabase.salesDao()
                    salesDao.deleteAll()

                    val sales1 = Sales(
                        id = System.currentTimeMillis(), quantity = 0,
                        amount = 0.0, creationDate = Calendar.getInstance().time,
                        dateOfSale = Calendar.getInstance().time
                    )

                    val sales2 = Sales(
                        id = System.currentTimeMillis() + 1000, quantity = 0,
                        amount = 0.0, creationDate = Calendar.getInstance().time,
                        dateOfSale = Calendar.getInstance().time
                    )

                    val sales3 = Sales(
                        id = System.currentTimeMillis() + 1000000, quantity = 0,
                        amount = 0.0, creationDate = Calendar.getInstance().time,
                        dateOfSale = Calendar.getInstance().time
                    )

                    salesDao.insertSales(sales1)
                    salesDao.insertSales(sales2)
                    salesDao.insertSales(sales3)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SalesRoomDatabase? = null
        private const val DB_NAME = "MIXED_SALES"

        fun getDatabase(context: Context, scope: CoroutineScope): SalesRoomDatabase =
            INSTANCE ?: INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    SalesRoomDatabase::class.java,
                    DB_NAME
                )
                    .addCallback(SalesDatabaseCallback(scope))
                    .build().also {
                        INSTANCE = it
                    }
            }
    }
}