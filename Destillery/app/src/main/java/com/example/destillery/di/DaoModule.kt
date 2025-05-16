package com.example.destillery.di

import com.example.destillery.database.ItemsDao
import com.example.destillery.database.ItemsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    @Singleton
    fun provideDao(itemsDatabase: ItemsDatabase) : ItemsDao {
        return itemsDatabase.itemsDao()
    }
}