package com.example.frontstore.domain.repository

import com.example.frontstore.domain.model.Articulo

interface ArticuloRepository {
    suspend fun getArticulos(): List<Articulo>
    suspend fun getArticuloById(id: String): Articulo
    suspend fun getArticulosPorCategoria(categoriaId: String): List<Articulo>
}
