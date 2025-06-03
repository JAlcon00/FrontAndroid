package com.example.frontstore.data.remote.api

import com.example.frontstore.data.model.CategoriaDto
import retrofit2.http.GET

interface CategoriaApi {
    @GET("categorias")
    suspend fun getCategorias(): List<CategoriaDto>
}
