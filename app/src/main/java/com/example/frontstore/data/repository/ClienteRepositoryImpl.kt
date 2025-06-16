package com.example.frontstore.data.repository

import com.example.frontstore.data.model.UsuarioDto
import com.example.frontstore.data.remote.api.ClienteApi
import com.example.frontstore.domain.repository.ClienteRepository
import javax.inject.Inject

class ClienteRepositoryImpl @Inject constructor(
    private val api: ClienteApi
) : ClienteRepository {

    override suspend fun getClienteById(id: String): UsuarioDto {
        val dto = api.getClienteById(id = id)
        return UsuarioDto(
            _id = dto._id,
            nombre = dto.nombre,
            apellido = dto.apellido,
            email = dto.email,
            password = dto.password,
            telefono = dto.telefono,
            direccion = dto.direccion,
            fechaCreacion = dto.fechaCreacion,
            fechaActualizacion = dto.fechaActualizacion,
            activo = dto.activo
        )
    }
}