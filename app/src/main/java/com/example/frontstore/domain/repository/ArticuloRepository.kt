package com.example.frontstore.domain.repository

import com.example.frontstore.data.model.ArticuloDto
import com.example.frontstore.data.remote.api.ArticuloApi
import com.example.frontstore.domain.model.Articulo
import retrofit2.http.GET
import javax.inject.Inject

interface ArticuloRepository {
    @GET("articulos")
    suspend fun getArticulos(): List<ArticuloDto>

    @GET("articulos/{id}")
    suspend fun getArticuloById(id: String): ArticuloDto

    suspend fun getArticulosPorCategoria(categoriaId: String): List<Articulo> {
        return TODO("Provide the return value")
    }
}
