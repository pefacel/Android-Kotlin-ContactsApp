package com.pefacel.contactapp2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ContactEntity::class], version = 4)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}



