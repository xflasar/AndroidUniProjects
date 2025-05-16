package com.example.destillery.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IDestilleryLocalRepositoryImpl @Inject constructor(private val dao: ItemsDao) : IDestilleryLocalRepository {
    override suspend fun insert(item: Item) {
        return dao.insert(item)
    }

    override suspend fun getAll(): Flow<List<Item>> {
        return dao.getAll()
    }

    override suspend fun stats(): Pair<String, String> {
        return Pair(dao.totalKg(), dao.totalCount())
    }
}