package com.pefacel.contactapp2.data.network

import com.pefacel.contactapp2.data.model.ContactResponse
import retrofit2.Response
import retrofit2.http.GET

interface ContactApiClient {
    @GET("?results=20&exc=login,registered,dob,id&noinfo")
    suspend fun getContacts(): Response<ContactResponse>
}