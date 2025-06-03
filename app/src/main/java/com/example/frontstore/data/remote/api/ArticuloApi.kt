package com.example.frontstore.data.remote.api

import com.example.frontstore.data.model.ArticuloDto

interface ArticuloApi {
    @GET("articulos")
    suspend fun getArticulos(): List<ArticuloDto>

    @GET("articulos/{id}")
    suspend fun getArticuloById(@Path("id") id: String): ArticuloDto

    @GET("articulos/categoria/{categoria}")
    suspend fun getArticulosPorCategoria(@Path("categoria") categoriaId: String): List<ArticuloDto>
}
