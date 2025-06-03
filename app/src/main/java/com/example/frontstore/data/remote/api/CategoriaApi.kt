package com.example.frontstore.data.remote.api

import com.example.frontstore.data.model.CategoriaDto

interface CategoriaApi {
    @GET("categorias")
    suspend fun getCategorias(): List<CategoriaDto>
}
