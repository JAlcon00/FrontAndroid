package com.example.frontstore.data.model

data class UsuarioDto(
    val _id: String,
    val nombre: String,
    val email: String,
    val direccion: String,
    val telefono: String,
    val rol: String,
    val fechaCreacion: String,
    val activo: Boolean
)
