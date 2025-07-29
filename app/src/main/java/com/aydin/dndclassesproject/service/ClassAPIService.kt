package com.aydin.dndclassesproject.service

import com.aydin.dndclassesproject.model.Class
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClassAPIService {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://raw.githubusercontent.com/AlaaddinAydin/test-api/refs/heads/main/")
        .build()
        .create(ClassAPI::class.java)

    suspend fun getData() : List<Class> = retrofit.getClass()
}