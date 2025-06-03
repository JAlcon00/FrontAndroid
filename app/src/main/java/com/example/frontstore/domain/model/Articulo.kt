package com.example.frontstore.domain.model

data class Articulo(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val stock: Int,
    val categoriaId: String,
    val imagenUrl: String?,
    val activo: Boolean
)
