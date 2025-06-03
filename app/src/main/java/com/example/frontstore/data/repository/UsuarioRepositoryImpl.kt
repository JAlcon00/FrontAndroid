package com.example.frontstore.data.repository

import com.example.frontstore.data.model.UsuarioDto
import com.example.frontstore.data.remote.api.UsuarioApi
import com.example.frontstore.domain.model.Usuario
import com.example.frontstore.domain.repository.UsuarioRepository
import javax.inject.Inject

class UsuarioRepositoryImpl @Inject constructor(
    private val api: UsuarioApi
) : UsuarioRepository {
    override suspend fun login(email: String, password: String): Usuario {
        val body = mapOf("email" to email, "password" to password)
        val response = api.loginUsuario(body)
        val dto = response.user
        return Usuario(
            id = dto._id,
            nombre = dto.nombre,
            email = dto.email,
            direccion = dto.direccion,
            telefono = dto.telefono,
            rol = dto.rol
        )
    }

    override suspend fun register(usuario: Usuario): Usuario {
        // Convertir Usuario → UsuarioDto para enviar a API
        val dto = UsuarioDto(
            _id = "",              // la API ignora _id en registro
            nombre = usuario.nombre,
            email = usuario.email,
            direccion = usuario.direccion,
            telefono = usuario.telefono,
            rol = "cliente",
            fechaCreacion = "",    // la API lo genera
            activo = true
        )
        val created = api.registerUsuario(dto)
        return Usuario(
            id = created._id,
            nombre = created.nombre,
            email = created.email,
            direccion = created.direccion,
            telefono = created.telefono,
            rol = created.rol
        )
    }
}
