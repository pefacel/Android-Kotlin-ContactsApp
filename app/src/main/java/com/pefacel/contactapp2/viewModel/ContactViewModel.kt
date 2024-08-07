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


    fun getContacts() {
        viewModelScope.launch {
            val result = contactService.getContacts()
            if (result.isNotEmpty()) {
                println("Contacts desde el viewModel" + contacts)
                contacts.postValue(result)
            }
        }
    }

    fun setCurrentContact(contact: ContactEntity?) {
        currentContact.postValue(contact)
    }


}