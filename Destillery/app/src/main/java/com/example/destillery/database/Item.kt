package com.example.destillery.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    var name: String,
    var volume: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
