package com.example.frontstore.domain.model

data class Usuario(
    val id: String,
    val nombre: String,
    val email: String,
    val direccion: String,
    val telefono: String,
    val rol: String
)
