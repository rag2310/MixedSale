package com.example.mixedsale

import com.example.mixedsale.data.db.SalesRoomDatabase
import android.app.Application
import com.example.mixedsale.data.repository.SalesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SalesApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {
        SalesRoomDatabase.getDatabase(this, applicationScope)
    }

    val repository by lazy {
        SalesRepository(database.salesDao())
    }
}