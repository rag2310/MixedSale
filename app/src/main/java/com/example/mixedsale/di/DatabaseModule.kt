package com.example.mixedsale.di

import android.content.Context
import androidx.room.Room
import com.example.mixedsale.data.db.SalesRoomDatabase
import com.example.mixedsale.data.db.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): SalesRoomDatabase = Room.databaseBuilder(
        appContext,
        SalesRoomDatabase::class.java,
        "MIXED_SALES"
    ).build()

    @Provides
    fun provideProductDao(database: SalesRoomDatabase): ProductDao {
        return database.productDao()
    }


}