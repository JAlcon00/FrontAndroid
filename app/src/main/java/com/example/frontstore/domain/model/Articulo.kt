package com.example.frontstore.domain.model

data class Articulo(
    val _id: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val stock: Int,
    val categoria: String,
    val activo: Boolean,
    val imagenes: List<String>,
    val fechaCreacion: String
)
