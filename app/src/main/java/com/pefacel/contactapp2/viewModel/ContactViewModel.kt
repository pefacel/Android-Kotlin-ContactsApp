package com.pefacel.contactapp2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pefacel.contactapp2.data.database.ContactEntity
import com.pefacel.contactapp2.data.model.ContactModel
import com.pefacel.contactapp2.data.service.ContactService
import kotlinx.coroutines.launch

class ContactViewModel : ViewModel() {

    private val contactService = ContactService()

    // Contacto actual
    val currentContact = MutableLiveData<ContactEntity?>(null)

    val contacts = MutableLiveData<List<ContactEntity>>()

    var contactsListFromDB: List<ContactEntity> = emptyList()

    fun getContacts() {
        viewModelScope.launch {
            val result = contactService.getContacts()
            if (result.isNotEmpty()) {
                println("Contacts desde el viewModel" + contacts)
                contacts.postValue(result)
                contactsListFromDB = result
            }
        }
    }

    fun setCurrentContact(contact: ContactEntity?) {
        currentContact.postValue(contact)
    }

    fun filterContactList(charSequence: CharSequence) {

        println("Sequencia de letras mostradas desde el ViewModel")
        println(charSequence)

        val contactsFiltered = contactsListFromDB.filter { contact ->
            contact.name.contains(charSequence, ignoreCase = true)
                    || contact.email.contains(charSequence, ignoreCase = true)
        }

        contacts.postValue(contactsFiltered)

    }

    fun resetContactList() {
        contacts.postValue(contactsListFromDB)
    }

    fun updateContact() {
        viewModelScope.launch {


            val contactUpdated = currentContact.value?.copy(favorite = !currentContact.value!!.favorite)
            if (contactUpdated != null) {
                contactService.updateContact(contactUpdated)

                currentContact.postValue(contactUpdated)

            }


        }
    }


}