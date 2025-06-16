package com.example.frontstore.domain.repository

import com.example.frontstore.data.model.UsuarioDto
import retrofit2.http.GET

interface ClienteRepository {

    @GET("clientes/{id}")
    suspend fun getClienteById(id : String): UsuarioDto
}