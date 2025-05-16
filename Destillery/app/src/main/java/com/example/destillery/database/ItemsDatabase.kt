package com.example.destillery.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = true)
abstract class ItemsDatabase : RoomDatabase() {
    abstract fun itemsDao(): ItemsDao

    companion object {
        private var INSTANCE: ItemsDatabase? = null

        fun getDatabase(context: Context) : ItemsDatabase {
            if(INSTANCE == null) {
                synchronized(ItemsDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ItemsDatabase::class.java,
                        "items_database"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE!!
        }
    }
}