package com.aydin.dndclassesproject.service

import com.aydin.dndclassesproject.model.Class
import retrofit2.http.GET

interface ClassAPI {
    @GET("classes.json?token=GHSAT0AAAAAADHQPFMHD4N7JHWYESTLUJHY2EIZIHA")
    suspend fun getClass() : List<Class>
}