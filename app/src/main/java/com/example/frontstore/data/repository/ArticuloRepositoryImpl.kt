package com.example.frontstore.data.repository

import com.example.frontstore.data.remote.api.ArticuloApi
import com.example.frontstore.domain.model.Articulo
import com.example.frontstore.domain.repository.ArticuloRepository
import javax.inject.Inject

class ArticuloRepositoryImpl @Inject constructor(
    private val api: ArticuloApi
) : ArticuloRepository {
    override suspend fun getArticulos(): List<Articulo> {
        val dtos = api.getArticulos()
        return dtos.map { dto ->
            Articulo(
                id = dto._id,
                nombre = dto.nombre,
                descripcion = dto.descripcion,
                precio = dto.precio,
                stock = dto.stock,
                categoriaId = dto.categoria,
                imagenUrl = dto.imagenUrl,
                activo = dto.activo
            )
        }
    }

    override suspend fun getArticuloById(id: String): Articulo {
        val dto = api.getArticuloById(id)
        return Articulo(
            id = dto._id,
            nombre = dto.nombre,
            descripcion = dto.descripcion,
            precio = dto.precio,
            stock = dto.stock,
            categoriaId = dto.categoria,
            imagenUrl = dto.imagenUrl,
            activo = dto.activo
        )
    }

    override suspend fun getArticulosPorCategoria(categoriaId: String): List<Articulo> {
        val dtos = api.getArticulosPorCategoria(categoriaId)
        return dtos.map { dto ->
            Articulo(
                id = dto._id,
                nombre = dto.nombre,
                descripcion = dto.descripcion,
                precio = dto.precio,
                stock = dto.stock,
                categoriaId = dto.categoria,
                imagenUrl = dto.imagenUrl,
                activo = dto.activo
            )
        }
    }
}
