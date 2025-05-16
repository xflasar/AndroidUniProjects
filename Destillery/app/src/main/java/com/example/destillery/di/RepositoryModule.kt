package com.example.destillery.di

import com.example.destillery.database.IDestilleryLocalRepository
import com.example.destillery.database.IDestilleryLocalRepositoryImpl
import com.example.destillery.database.ItemsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(dao: ItemsDao) : IDestilleryLocalRepository {
        return IDestilleryLocalRepositoryImpl(dao)
    }
}