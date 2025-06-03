package com.example.frontstore.domain.repository

import com.example.frontstore.domain.model.Usuario

interface UsuarioRepository {
    suspend fun login(email: String, password: String): Usuario
    suspend fun register(usuario: Usuario): Usuario
}
