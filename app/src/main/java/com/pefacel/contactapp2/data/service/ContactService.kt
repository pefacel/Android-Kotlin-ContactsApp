package com.pefacel.contactapp2.data.service

import com.pefacel.contactapp2.ContactApp
import com.pefacel.contactapp2.data.database.ContactDao
import com.pefacel.contactapp2.data.database.ContactEntity
import com.pefacel.contactapp2.data.model.ContactModel
import com.pefacel.contactapp2.data.network.ContactApiClient
import com.pefacel.contactapp2.data.provider.ContactProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactService {


    private val contactProvider = ContactProvider()

    suspend fun getContacts(): List<ContactEntity> {

        return withContext(Dispatchers.IO) {
            //        deleteAllContacts()

            // 1. Checkear si hay contactos guardados en nuestra Databse
            val contactsFromDatabase = getContactsFromDatabase()

            // 2. Si no hay contactos, que haga un fetch desde la api y guarde esos contactos
            if (contactsFromDatabase.isEmpty()) {
                val contactsFromApi = getContactsFromApi()
                saveAllContactOnDatabase(contactsFromApi)
            }


            println("contactsFromDatabase")
            println(contactsFromDatabase)


            contactsFromDatabase
        }


    }


    suspend fun saveContact(contact: ContactEntity) {
        ContactApp.database.contactDao().saveContact(contact)
    }

    suspend fun deleteAllContacts() {
        ContactApp.database.contactDao().deleteAllContacts()
    }

    suspend fun saveAllContactOnDatabase(contacts: List<ContactModel>) {
        contacts.forEach { contact ->
            val newContact = ContactEntity(id = 0, name = contact.name.first, email = contact.email)
            saveContact(newContact)
        }

    }


    suspend fun getContactsFromDatabase(): List<ContactEntity> {
        return ContactApp.database.contactDao().getAllContacts()
    }

    suspend fun getContactsFromApi(): List<ContactModel> {
        val response =
            contactProvider.provideRetrofit().create(ContactApiClient::class.java).getContacts()
        return response.body()?.results ?: emptyList()
    }


    suspend fun updateContact(contact: ContactEntity) {
        return ContactApp.database.contactDao().updateContact(contact)
    }


}