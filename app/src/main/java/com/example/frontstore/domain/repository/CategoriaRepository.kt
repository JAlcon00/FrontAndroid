package com.example.frontstore.domain.repository

import com.example.frontstore.domain.model.Categoria

interface CategoriaRepository {
    suspend fun getCategorias(): List<Categoria>
}
