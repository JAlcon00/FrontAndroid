package com.example.frontstore.domain.model

data class Categoria(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val imagenUrl: String?,
    val activo: Boolean
)

