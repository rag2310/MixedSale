package com.example.mixedsale.ui


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.mixedsale.R
import com.example.mixedsale.SalesApplication
import com.example.mixedsale.data.model.Sales
import com.example.mixedsale.ui.sales.SalesViewModel
import com.example.mixedsale.ui.sales.SalesViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

class MainActivity : AppCompatActivity() {
    private val salesViewModel: SalesViewModel by viewModels {
        SalesViewModelFactory((application as SalesApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


    /*private suspend fun createDatabase() {

        coroutineScope {
            launch {
                val appDatabase: AppDatabase = AppDatabase.getDatabase(applicationContext)
                appDatabase.let {
                    val sales1 = Sales(
                        id = System.currentTimeMillis(), quantity = 0,
                        amount = 0.0, creationDate = Calendar.getInstance().time,
                        dateOfSale = Calendar.getInstance().time
                    )

                    it.salesDao().insertSales(sales1)

                }
            }
        }

        *//*runBlocking {
            launch {
                val appDatabase: AppDatabase = AppDatabase.getDatabase(applicationContext)
                appDatabase.let {
                    val sales1 = Sales(
                        id = System.currentTimeMillis(), quantity = 0,
                        amount = 0.0, creationDate = Calendar.getInstance().time,
                        dateOfSale = Calendar.getInstance().time
                    )

                    it.salesDao().insertSales(sales1)
                }
            }
        }*//*
    }*/
}