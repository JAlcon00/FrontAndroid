package com.example.frontstore.domain.repository

import com.example.frontstore.data.model.CategoriaDto
import com.example.frontstore.domain.model.Categoria
import retrofit2.http.GET

interface CategoriaRepository {

    @GET("categorias")
    suspend fun getCategorias(): List<CategoriaDto>
}
