package com.example.mixedsale

import com.example.mixedsale.data.db.SalesRoomDatabase
import android.app.Application
import com.example.mixedsale.data.repository.SalesRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


@HiltAndroidApp
class SalesApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {
        SalesRoomDatabase.getDatabase(this, applicationScope)
    }

    val repository by lazy {
        SalesRepository(database.salesDao())
    }
}