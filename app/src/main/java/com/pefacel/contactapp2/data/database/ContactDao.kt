package com.pefacel.contactapp2.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact_entity")
    suspend fun getAllContacts(): List<ContactEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveContact(contact: ContactEntity)

    @Query("DELETE  FROM contact_entity")
    suspend fun deleteAllContacts()


//    @Update
//    suspend fun updateContact(contact: ContactEntity)

    // 1. Actualizar el entity con un booleano favorite por default en false
    // 2. Crear una función en el dao que busque y actualice el booleano favorite true -> false o de false -> TRUE


}