package com.example.destillery.database

import kotlinx.coroutines.flow.Flow

interface IDestilleryLocalRepository {
    suspend fun insert(item: Item)
    suspend fun getAll(): Flow<List<Item>>
    suspend fun stats(): Pair<String, String>
}