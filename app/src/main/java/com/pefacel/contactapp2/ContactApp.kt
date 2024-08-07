package com.pefacel.contactapp2

import android.app.Application
import com.pefacel.contactapp2.data.database.ContactDatabase
import com.pefacel.contactapp2.data.provider.ContactProvider

class ContactApp : Application() {


    private val contactProvider = ContactProvider()


    companion object {
        lateinit var database: ContactDatabase
    }


    override fun onCreate() {
        super.onCreate()
        database = contactProvider.provideRoom(this)

    }

}