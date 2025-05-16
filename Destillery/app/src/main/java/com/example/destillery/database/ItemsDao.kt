package com.example.destillery.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemsDao {
    @Insert
    suspend fun insert(item: Item)

    @Query("SELECT * FROM items")
    fun getAll(): Flow<List<Item>>

    @Query("SELECT SUM(volume) FROM items")
    suspend fun totalKg(): String

    @Query("SELECT COUNT(*) FROM items")
    suspend fun totalCount(): String

}