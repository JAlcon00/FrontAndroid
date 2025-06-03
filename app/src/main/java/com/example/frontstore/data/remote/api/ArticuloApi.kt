package com.example.frontstore.data.remote.api

import com.example.frontstore.data.model.ArticuloDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticuloApi {
    @GET("articulos")
    suspend fun getArticulos(): List<ArticuloDto>

    @GET("articulos/{id}")
    suspend fun getArticuloById(@Path("id") id: String): ArticuloDto

    @GET("articulos/categoria/{categoria}")
    suspend fun getArticulosPorCategoria(@Path("categoria") categoriaId: String): List<ArticuloDto>
}
