package com.aydin.dndclassesproject.service

import com.aydin.dndclassesproject.model.Class
import retrofit2.http.GET

interface ClassAPI {
    @GET("classes.json?token=GHSAT0AAAAAADHQPFMHJJJKXCBQ3QLAXXZY2EJZW2A")
    suspend fun getClass() : List<Class>
}