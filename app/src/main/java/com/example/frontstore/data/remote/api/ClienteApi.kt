package com.example.frontstore.data.remote.api

import com.example.frontstore.data.model.UsuarioDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ClienteApi {
    @GET("clientes/{id}")
    suspend fun getClienteById(@Path("id") id: String) : UsuarioDto
}