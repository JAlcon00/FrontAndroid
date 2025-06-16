package com.example.frontstore.data.repository

import android.util.Log
import com.example.frontstore.data.model.ArticuloDto
import com.example.frontstore.data.remote.api.ArticuloApi
import com.example.frontstore.domain.model.Articulo
import com.example.frontstore.domain.repository.ArticuloRepository
import javax.inject.Inject

class ArticuloRepositoryImpl @Inject constructor(
    private val api: ArticuloApi
) : ArticuloRepository {

    override suspend fun getArticulos(): List<ArticuloDto> {
        val dtos = api.getArticulos()
        return dtos.map { dto ->
            ArticuloDto(
                _id = dto._id,
                nombre = dto.nombre,
                descripcion = dto.descripcion,
                precio = dto.precio,
                stock = dto.stock,
                categoria = dto.categoria,
                activo = dto.activo,
                imagenes = dto.imagenes,
                fechaCreacion = dto.fechaCreacion
            )
        }
    }

    override suspend fun getArticuloById(id: String): ArticuloDto {
        val dto = api.getArticuloById(id)
        return ArticuloDto(
            _id = dto._id,
            nombre = dto.nombre,
            descripcion = dto.descripcion,
            precio = dto.precio,
            stock = dto.stock,
            categoria = dto.categoria,
            activo = dto.activo,
            imagenes = dto.imagenes,
            fechaCreacion = dto.fechaCreacion
        )
    }

    override suspend fun getArticulosPorCategoria(categoriaId: String): List<Articulo> {
        val dtos = api.getArticulosPorCategoria(categoriaId)
        return dtos.map { dto ->
            Articulo(
                _id = dto._id,
                nombre = dto.nombre,
                descripcion = dto.descripcion,
                precio = dto.precio,
                stock = dto.stock,
                categoria = dto.categoria,
                activo = dto.activo,
                imagenes = dto.imagenes,
                fechaCreacion = dto.fechaCreacion
            )
        }
    }
}
