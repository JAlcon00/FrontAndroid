package com.example.frontstore.data.model

data class UsuarioDto(
    val _id: String,
    val nombre: String,
    val apellido: String,
    val email: String,
    val password : String,
    val telefono: String,
    val direccion: String,
    val fechaCreacion: String,
    val fechaActualizacion: String,
    val activo: Boolean
)
