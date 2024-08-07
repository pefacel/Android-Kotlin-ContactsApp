package com.pefacel.contactapp2.data.provider

import android.content.Context
import androidx.room.Room
import com.pefacel.contactapp2.data.database.ContactDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContactProvider {

    val CONTACT_DATABASE_NAME = "contact-db2"
    val RANDOMUSER_ENDPOINT = "https://randomuser.me/api/"

    fun provideRetrofit(): Retrofit {
        val endpointUrl = RANDOMUSER_ENDPOINT
        return Retrofit.Builder()
            .baseUrl(endpointUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun provideRoom(context: Context): ContactDatabase {

        return Room.databaseBuilder(context, ContactDatabase::class.java, CONTACT_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


}