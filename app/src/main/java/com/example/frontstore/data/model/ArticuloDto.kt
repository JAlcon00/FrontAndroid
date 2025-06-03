package com.example.frontstore.data.model

data class ArticuloDto(
    val _id: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val stock: Int,
    val categoria: String,
    val imagenUrl: String?,
    val fechaCreacion: String,
    val activo: Boolean
)
