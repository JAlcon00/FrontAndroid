package com.example.frontstore.data.repository

import com.example.frontstore.data.remote.api.CategoriaApi
import com.example.frontstore.domain.model.Categoria
import com.example.frontstore.domain.repository.CategoriaRepository
import javax.inject.Inject

class CategoriaRepositoryImpl @Inject constructor(
    private val api: CategoriaApi
) : CategoriaRepository {
    override suspend fun getCategorias(): List<Categoria> {
        val dtos = api.getCategorias()
        return dtos.map { dto ->
            Categoria(
                id = dto._id,
                nombre = dto.nombre,
                descripcion = dto.descripcion,
                imagenUrl = dto.imagenUrl,
                activo = dto.activo
            )
        }
    }
}
